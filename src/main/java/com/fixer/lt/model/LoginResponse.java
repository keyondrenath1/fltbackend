package com.fixer.lt.model;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private static final long serialVersionUID = 2778776566573109757L;

    private String token;
    private String email;
    private long accountId;
    private String firstname;
    private String lastname;
    private String accountType;
    private Boolean enabled;

    public LoginResponse() {
    }

    public LoginResponse(String token, String email, long accountId, String firstname, String lastname, String accountType, Boolean enabled) {
        this.token = token;
        this.email = email;
        this.accountId = accountId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.accountType = accountType;
        this.enabled = enabled;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}