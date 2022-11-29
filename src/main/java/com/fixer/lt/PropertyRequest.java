package com.fixer.lt;

import com.fixer.lt.entity.Address;
import com.fixer.lt.entity.PropertyUnit;

import java.util.List;

public class PropertyRequest {
  private long landlordId;
  private Address address;
  private PropertyUnit propertyUnit;
  private List<String> emails;

  public PropertyRequest(long landlordId, Address address, PropertyUnit propertyUnit, List<String> emails) {
    this.landlordId = landlordId;
    this.address = address;
    this.propertyUnit = propertyUnit;
    this.emails = emails;
  }

  public List<String> getEmails() {
    return emails;
  }

  public void setEmails(List<String> emails) {
    this.emails = emails;
  }

  public long getLandlordId() {
    return landlordId;
  }

  public void setLandlordId(long landlordId) {
    this.landlordId = landlordId;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public PropertyUnit getPropertyUnit() {
    return propertyUnit;
  }

  public void setPropertyUnit(PropertyUnit propertyUnit) {
    this.propertyUnit = propertyUnit;
  }
}
