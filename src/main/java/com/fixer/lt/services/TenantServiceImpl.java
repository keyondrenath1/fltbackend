package com.fixer.lt.services;

import com.fixer.lt.entity.Tenant;
import com.fixer.lt.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenantServiceImpl implements TenantService {

  private TenantRepository tenantRepository;

  @Autowired
  public TenantServiceImpl(TenantRepository tenantRepository) {
    this.tenantRepository = tenantRepository;
  }

  @Override
  public Tenant createTenant(Tenant newTenant) {
    return tenantRepository.save(newTenant);
  }

  @Override
  public Optional<Tenant> findTenantById(long id) {
    return tenantRepository.findById(id);
  }

  @Override
  public List<Tenant> getTenants() {
    return tenantRepository.findAll();
  }
}