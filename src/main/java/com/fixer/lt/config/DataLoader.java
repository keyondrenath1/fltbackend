package com.fixer.lt.config;

import com.fixer.lt.entity.Account;
import com.fixer.lt.entity.Landlord;
import com.fixer.lt.model.AccountType;
import com.fixer.lt.model.NewUser;
import com.fixer.lt.repository.AuthenticationRepository;
import com.fixer.lt.repository.LandlordRepository;

import com.fixer.lt.repository.PropertyRepository;

import com.fixer.lt.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private AuthenticationService authenticationService;

    @Autowired
    public DataLoader(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Bean
    public CommandLineRunner demo(AuthenticationService authenticationService) {
        return (args) -> {
            NewUser landlord = new NewUser();
            landlord.setAccountType(1);
            landlord.setEmail("landlord@email.com");
            landlord.setPassword("password");
            landlord.setFirst_name("Jon");
            landlord.setLast_name("Doe");
            authenticationService.createAccount(landlord);

            NewUser tenant = new NewUser();
            tenant.setAccountType(2);
            tenant.setEmail("tenant@email.com");
            tenant.setPassword("password");
            tenant.setFirst_name("Mike");
            tenant.setLast_name("Jason");
            authenticationService.createAccount(tenant);
        };
    }
}