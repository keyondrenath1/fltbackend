package com.fixer.lt.model;

import com.fixer.lt.entity.FaultType;

public class FaultRequest {
    private FaultType faultType;
    private String description;
    private String user;

    public FaultType getFaultType() {
        return faultType;
    }

    public void setFaultType(FaultType faultType) {
        this.faultType = faultType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
