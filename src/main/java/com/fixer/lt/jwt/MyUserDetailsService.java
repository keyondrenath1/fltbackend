package com.fixer.lt.jwt;

import com.fixer.lt.entity.Account;
import com.fixer.lt.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthenticationRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(s);
        if (account == null) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", s));
        }
        return new MyUserPrincipal(account);
    }

}