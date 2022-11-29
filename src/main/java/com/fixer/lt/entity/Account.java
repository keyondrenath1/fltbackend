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
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String email;
  private String password;
  private boolean enabled;
  private String accountType;

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
   * @return email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email
   * @param email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password
   * @param password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Check whether user is verified
   * @return
   */
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * set user verification
   * @return
   */
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  /**
   * Get account type
   * @return
   */
  public String getAccountType() {
    return accountType;
  }

  /**
   * Set account type
   * @param accountType
   */
  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }
}
