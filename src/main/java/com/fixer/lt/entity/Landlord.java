package com.fixer.lt.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Entity class Landlord which maps to Landlord table in the database via JPA ORM tool
 */
@Entity
public class Landlord {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id", referencedColumnName = "id")
  private Account account;

  @OneToMany(mappedBy = "landlord", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Property> properties = new ArrayList<>();

  private String first_name;
  private String last_name;

  public void addProperty(Property property) {
    properties.add(property);
    property.setLandlord(this);
  }

  public void removeProperty(Property property) {
    properties.remove(property);
    property.setLandlord(null);
  }

  /**
   * @return id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the id
   *
   * @param id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * @return first_name
   */
  public String getFirst_name() {
    return first_name;
  }

  /**
   * Sets the first_name
   *
   * @param first_name
   */
  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  /**
   * @return last_name
   */
  public String getLast_name() {
    return last_name;
  }

  /**
   * Sets the last_name
   *
   * @param last_name
   */
  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }


  public List<Property> getProperties() {
    return properties;
  }
}