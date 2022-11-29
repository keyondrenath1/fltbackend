package com.fixer.lt.services;

import com.fixer.lt.PropertyRequest;
import com.fixer.lt.entity.Account;
import com.fixer.lt.entity.Landlord;
import com.fixer.lt.entity.Property;
import com.fixer.lt.entity.Tenant;
import com.fixer.lt.repository.AccountRepository;
import com.fixer.lt.repository.PropertyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fixer.lt.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl implements PropertyService {

  private PropertyRepository propertyRepository;
  private LandlordService landlordService;
  private AccountRepository accountRepository;
  private TenantRepository tenantRepository;

  @Autowired
  public PropertyServiceImpl(PropertyRepository propertyRepository, LandlordService landlordService, AccountRepository accountRepository, TenantRepository tenantRepository) {
    this.propertyRepository = propertyRepository;
    this.landlordService = landlordService;
    this.accountRepository = accountRepository;
    this.tenantRepository = tenantRepository;
  }

  @Override
  public Property createProperty(PropertyRequest propertyRequest) {
    Landlord landlord = landlordService.findLandlordById(propertyRequest.getLandlordId());

    Property newProperty = new Property();
    newProperty.setLandlord(landlord);
    newProperty.setAddress(propertyRequest.getAddress());
    newProperty.setPropertyUnit(propertyRequest.getPropertyUnit());

    List<String> emails = propertyRequest.getEmails();
    List<Tenant> tenants = new ArrayList<>();

    for (String email: emails) {
      Account account = accountRepository.findByEmail(email);
      if (account != null) {
        Tenant tenant = tenantRepository.findTenantByAccount(account);
        tenant.setProperty(newProperty);
        tenants.add(tenant);
      }
    }
    newProperty.setTenants(tenants);
    return propertyRepository.save(newProperty);
  }

  @Override
  public List<Property> getProperties(long landlordId) {
    Landlord landlord = landlordService.findLandlordById(landlordId);
    return propertyRepository.findAllByLandlord(landlord);
  }

  @Override
  public Property findPropertyById(long id) {
    Optional<Property> optionalProperty = propertyRepository.findById(id);
    return optionalProperty.get();
  }


}