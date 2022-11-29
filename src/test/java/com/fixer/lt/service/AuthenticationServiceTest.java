package com.fixer.lt.service;

import com.fixer.lt.entity.Account;
import com.fixer.lt.entity.Landlord;
import com.fixer.lt.entity.Tenant;
import com.fixer.lt.jwt.JwtTokenUtil;
import com.fixer.lt.jwt.MyUserPrincipal;
import com.fixer.lt.model.LoginRequest;
import com.fixer.lt.model.LoginResponse;
import com.fixer.lt.model.NewUser;
import com.fixer.lt.repository.AuthenticationRepository;
import com.fixer.lt.repository.LandlordRepository;
import com.fixer.lt.repository.TenantRepository;
import com.fixer.lt.services.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    private static final String TEST_EMAIL= "some-email@email.com";
    private static final String TEST_FIRST_NAME= "Jon";
    private static final String TEST_LAST_NAME= "Doe";
    private static final String TEST_PASSWORD= "password";
    private static final String TEST_JWT_TOKEN= "test-jwt-token";

    AuthenticationService authenticationService;

    @Mock
    AuthenticationRepository authenticationRepository;
    @Mock
    LandlordRepository landlordRepository;
    @Mock
    TenantRepository tenantRepository;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    UserDetailsService userDetailsService;
    @Mock
    JwtTokenUtil jwtTokenUtil;

    @BeforeEach
    void setup() {
        authenticationService = new AuthenticationService(
                authenticationRepository,
                landlordRepository,
                tenantRepository,
                authenticationManager,
                userDetailsService,
                jwtTokenUtil
        );
    }

    @Test
    public void shouldCreateLandlordAccount() {
        Landlord landlord = new Landlord();
        Account account = new Account();
        account.setAccountType("1");
        landlord.setAccount(account);
        Mockito.when(landlordRepository.save(any(Landlord.class))).thenReturn(landlord);

        NewUser newUser = new NewUser();
        newUser.setAccountType(1);
        newUser.setPassword(TEST_PASSWORD);
        newUser.setFirst_name(landlord.getFirst_name());
        newUser.setLast_name(landlord.getLast_name());
        newUser.setEmail(TEST_EMAIL);
        boolean isAccountNull = authenticationService.createAccount(newUser);
        assertTrue(isAccountNull);
    }

    @Test
    public void shouldCreateTenantAccount() {
        Tenant tenant = new Tenant();
        Account account = new Account();
        account.setAccountType("2");
        tenant.setAccount(account);
        Mockito.when(tenantRepository.save(any(Tenant.class))).thenReturn(tenant);

        NewUser newUser = new NewUser();
        newUser.setAccountType(2);
        newUser.setPassword(TEST_PASSWORD);
        newUser.setFirst_name(tenant.getFirst_name());
        newUser.setLast_name(tenant.getLast_name());
        newUser.setEmail(TEST_EMAIL);
        boolean isAccountNull = authenticationService.createAccount(newUser);
        assertTrue(isAccountNull);
    }

    @Test
    public void shouldTestLogin() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(TEST_EMAIL);
        loginRequest.setPassword(TEST_PASSWORD);

        Account account = new Account();
        account.setId(1L);
        account.setAccountType("2");
        account.setEmail(TEST_EMAIL);

        Landlord landlord = new Landlord();
        landlord.setId(1L);
        landlord.setFirst_name(TEST_FIRST_NAME);
        landlord.setLast_name(TEST_LAST_NAME);
        landlord.setAccount(account);

        UserDetails userDetails = new MyUserPrincipal(account);
        Mockito.when(userDetailsService.loadUserByUsername(any(String.class))).thenReturn(userDetails);
        Mockito.when(jwtTokenUtil.generateToken(userDetails)).thenReturn(TEST_JWT_TOKEN);
        Mockito.when(authenticationRepository.findByEmail(any(String.class))).thenReturn(account);
        Mockito.when(landlordRepository.findLandlordByAccount(any(Account.class))).thenReturn(landlord);
        LoginResponse loginResponse = authenticationService.login(loginRequest);
        assertThat(loginResponse.getEmail(), is(loginRequest.getEmail()));
        assertThat(loginResponse.getToken(), is(TEST_JWT_TOKEN));
        assertThat(loginResponse.getFirstname(), is(TEST_FIRST_NAME));
        assertThat(loginResponse.getLastname(), is(TEST_LAST_NAME));
        assertThat(loginResponse.getAccountType(), is("2"));
        assertThat(loginResponse.getAccountId(), is(account.getId()));
    }

}
