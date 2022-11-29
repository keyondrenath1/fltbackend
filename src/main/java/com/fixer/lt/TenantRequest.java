package com.fixer.lt;

import com.fixer.lt.entity.Tenant;

public class TenantRequest {

  private long propertyId;
  private Tenant tenant;

  public long getPropertyById() {
    return propertyId;
  }

  public void setPropertyId(long propertyId) {
    this.propertyId = propertyId;
  }

  public Tenant getTenant() {
    return tenant;
  }

  public void setTenant(Tenant tenant) {
    this.tenant = tenant;
  }
}
