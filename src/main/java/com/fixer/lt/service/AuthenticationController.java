package com.fixer.lt.service;

import com.fixer.lt.exception.UserExistException;
import com.fixer.lt.model.LoginRequest;
import com.fixer.lt.model.LoginResponse;
import com.fixer.lt.model.NewUser;
import com.fixer.lt.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/authenticate")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewAccount(@RequestBody NewUser newUser) {
        try {
            authenticationService.createAccount(newUser);
        } catch (UserExistException e) {
            return new ResponseEntity("User exists with this email", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = null;
        try {
             loginResponse = authenticationService.login(loginRequest);
        } catch (BadCredentialsException e) {
            return new ResponseEntity("Email or Password is incorrect", HttpStatus.BAD_REQUEST);

        }
        return ResponseEntity.ok(loginResponse);
    }
}