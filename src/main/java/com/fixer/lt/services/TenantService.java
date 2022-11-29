package com.fixer.lt.services;

import com.fixer.lt.entity.Tenant;

import java.util.List;
import java.util.Optional;

public interface TenantService {

  Tenant createTenant(Tenant newTenant);
  Optional<Tenant> findTenantById(long id);
  List<Tenant> getTenants();
}
