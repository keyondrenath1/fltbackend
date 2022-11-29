package com.fixer.lt.services;

import com.fixer.lt.entity.Account;
import com.fixer.lt.entity.Landlord;
import com.fixer.lt.entity.Tenant;
import com.fixer.lt.exception.UserExistException;
import com.fixer.lt.exception.AuthenticationException;
import com.fixer.lt.jwt.JwtTokenUtil;
import com.fixer.lt.model.AccountType;
import com.fixer.lt.model.LoginRequest;
import com.fixer.lt.model.LoginResponse;
import com.fixer.lt.model.NewUser;
import com.fixer.lt.repository.AuthenticationRepository;
import com.fixer.lt.repository.LandlordRepository;
import com.fixer.lt.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {

    private AuthenticationRepository authenticationRepository;
    private LandlordRepository landlordRepository;
    private TenantRepository tenantRepository;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthenticationService(
            AuthenticationRepository authenticationRepository,
            LandlordRepository landlordRepository, TenantRepository tenantRepository,
            AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil
    ) {
        this.authenticationRepository = authenticationRepository;
        this.landlordRepository = landlordRepository;
        this.tenantRepository = tenantRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public boolean createAccount(NewUser newUser) throws UserExistException {

        String email = newUser.getEmail();
        String password = newUser.getPassword();
        int accountType = newUser.getAccountType();

        boolean isAccountNull = getAccount(email) == null;

        if (isAccountNull) {
            Account account = new Account();
            account.setEmail(email);
            account.setPassword(encryptPassword(password));
            account.setAccountType(determineAccountType(accountType));

            if (newUser.getAccountType() == 1) {
                Landlord landlord = new Landlord();
                landlord.setAccount(account);
                landlord.setFirst_name(newUser.getFirst_name());
                landlord.setLast_name(newUser.getLast_name());
                landlordRepository.save(landlord);

            } else {
                Tenant tenant = new Tenant();
                tenant.setAccount(account);
                tenant.setFirst_name(newUser.getFirst_name());
                tenant.setLast_name(newUser.getLast_name());
                tenantRepository.save(tenant);
            }

            authenticationRepository.save(account);
        }
         return isAccountNull;

    }

    public LoginResponse login(LoginRequest loginRequest) throws AuthenticationException {
        authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);

        Account account = authenticationRepository.findByEmail(loginRequest.getEmail());
        Landlord landlord = landlordRepository.findLandlordByAccount(account);

        if (landlord == null) {
            Tenant tenant = tenantRepository.findTenantByAccount(account);
            boolean enabled = tenant.getProperty() != null;
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setEmail(account.getEmail());
            loginResponse.setAccountId(tenant.getId());
            loginResponse.setFirstname(tenant.getFirst_name());
            loginResponse.setLastname(tenant.getLast_name());
            loginResponse.setAccountType(account.getAccountType());
            loginResponse.setEnabled(enabled);
            return loginResponse;
        }

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setEmail(account.getEmail());
        loginResponse.setAccountId(landlord.getId());
        loginResponse.setFirstname(landlord.getFirst_name());
        loginResponse.setLastname(landlord.getLast_name());
        loginResponse.setAccountType(account.getAccountType());
        loginResponse.setEnabled(null);

        return loginResponse;
    }

    private Account getAccount(String email) throws UserExistException {
        Account foundAccount = authenticationRepository.findByEmail(email);
        if (foundAccount != null) {
            throw new UserExistException(
                    String.format("There already exists a user with email=%s", email)
            );
        }
        return authenticationRepository.findByEmail(email);
    }

    private String encryptPassword(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(rawPassword);
    }

    private String determineAccountType(int accountType) {
        switch (accountType) {
            case 2:
                return AccountType.TENANT.toString();
            default:
                return AccountType.LANDLORD.toString();
        }
    }

    private void authenticate(String username, String password) throws AuthenticationException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}