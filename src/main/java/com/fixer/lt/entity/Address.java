package com.fixer.lt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class Account which maps to account table in the database via JPA ORM tool
 * {Research for Report}
 */
@Entity
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String streetAddress;
  private String city;
  private String postCode;
  private String country;

  /**
   * @return id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the id{only here for testing purposes}
   * @param id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * @return Street address
   */
  public String getStreetAddress() {
    return streetAddress;
  }

  /**
   * Sets the street address
   * @param streetAddress
   */
  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  /**
   * @return City
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets the city
   * @param city
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * @return Postcode
   */
  public String getPostCode() {
    return postCode;
  }

  /**
   * Sets the postcode
   * @param postCode
   */
  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  /**
   * @return Country
   */
  public String getCountry() {
    return country;
  }

  /**
   * Sets the country
   * @param country
   */
  public void setCountry(String country) {
    this.country = country;
  }
}
