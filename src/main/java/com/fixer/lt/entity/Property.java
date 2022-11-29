package com.fixer.lt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 * Entity class Property which maps to Property table in the database via JPA ORM tool {Research for
 * Report}
 */
@Entity
public class Property {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  private Landlord landlord;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "property_unit_id", referencedColumnName = "id")
  private PropertyUnit propertyUnit;

  @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Tenant> tenants = new ArrayList<>();

  @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Fault> faults = new ArrayList<>();

  /**
   * @return id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the id{only here for testing purposes}
   *
   * @param id
   */
  public void setId(long id) {
    this.id = id;
  }

  public Landlord getLandlord() {
    return landlord;
  }

  public void setLandlord(Landlord landlord) {
    this.landlord = landlord;
  }

  public void setTenants(List<Tenant> tenants) {
    this.tenants = tenants;
  }

  public void setFaults(List<Fault> faults) {
    this.faults = faults;
  }

  public List<Tenant> getTenants() {
    return tenants;
  }

  public List<Fault> getFaults() {
    return faults;
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



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Property property = (Property) o;
    return id == property.id &&
        landlord.equals(property.landlord);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, landlord);
  }
}
