package com.fixer.lt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Entity class Tenant which maps to tenant table in the database via JPA ORM tool
 */
@Entity
public class Tenant {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id", referencedColumnName = "id")
  private Account account;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  private Property property;

  private String first_name;
  private String last_name;

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

  public Property getProperty() {
    return property;
  }

  public void setProperty(Property property) {
    this.property = property;
  }
}
