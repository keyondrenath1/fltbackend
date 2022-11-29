package com.fixer.lt.services;

import com.fixer.lt.entity.Fault;
import com.fixer.lt.exception.PropertyException;
import com.fixer.lt.model.FaultRequest;

import java.util.List;

public interface FaultService {
    Fault createFault(FaultRequest fault) throws PropertyException;
    List<Fault> getFaults(long landlordId);
}
