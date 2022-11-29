package com.fixer.lt.services;

import com.fixer.lt.entity.*;
import com.fixer.lt.exception.PropertyException;
import com.fixer.lt.model.FaultRequest;
import com.fixer.lt.repository.AccountRepository;
import com.fixer.lt.repository.AuthenticationRepository;
import com.fixer.lt.repository.FaultRepository;
import com.fixer.lt.repository.TenantRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FaultServiceImpl implements FaultService {

    private FaultRepository faultRepository;
    private LandlordService landlordService;
    private TenantRepository tenantRepository;
    private AccountRepository accountRepository;

    @Autowired
    public FaultServiceImpl(FaultRepository faultRepository,
                            LandlordService landlordService,
                            TenantRepository tenantRepository,
                            AccountRepository accountRepository) {
        this.faultRepository = faultRepository;
        this.landlordService = landlordService;
        this.tenantRepository = tenantRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Fault createFault(FaultRequest faultRequest) throws PropertyException {
        Account account = accountRepository.findByEmail(faultRequest.getUser());
        Tenant tenant = tenantRepository.findTenantByAccount(account);
        Property property = tenant.getProperty();
        if (property == null) {
            throw new PropertyException("User not assigned to a user.");
        }
        Fault fault = new Fault();
        fault.setProperty(property);
        fault.setPropId(property.getId());
        fault.setTenantID(tenant.getId());
        fault.setReporter(tenant.getAccount().getEmail());
        fault.setType(faultRequest.getFaultType());
        fault.setDescription(faultRequest.getDescription());
        fault.setCompleted(false);
        fault.setStatus(Status.STARTED);
        fault.setReporterName(tenant.getFirst_name() + " " + tenant.getLast_name());
        fault.setFaultId(RandomStringUtils.random(6, true, true));
        return faultRepository.save(fault);
    }

    @Override
    public List<Fault> getFaults(long landlordId) {
        Landlord landlord = landlordService.findLandlordById(landlordId);
        List<Fault> faults = new ArrayList<>();
        landlord.getProperties().forEach(property -> {
            faults.addAll(property.getFaults());
        });
        return faults;
    }

    public List<Fault> getFaultsByTenant(String tenantEmail) {
        return faultRepository.findAllByReporter(tenantEmail);
    }

    public Fault updateFault(String faultId) {
        Fault fault = faultRepository.findByFaultId(faultId);
        fault.setCompleted(true);
        fault.setStatus(Status.DONE);
        return faultRepository.save(fault);
    }
}
