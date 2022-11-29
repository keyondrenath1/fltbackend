package com.fixer.lt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity class Account which maps to account table in the database via JPA ORM tool
 * {Research for Report}
 */
@Entity
public class Fault {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Enumerated(EnumType.STRING)
  private FaultType type;

  private String description;

  private String faultId;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  private Property property;

  private long tenantID;

  private String reporter;

  private boolean isCompleted;

  private Status status;

  private long propId;

  private String reporterName;

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

  public FaultType getType() {
    return type;
  }

  public void setType(FaultType type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Property getProperty() {
    return property;
  }

  public void setProperty(Property property) {
    this.property = property;
  }

  public long getTenantID() {
    return tenantID;
  }

  public void setTenantID(long tenantID) {
    this.tenantID = tenantID;
  }

  public boolean isCompleted() {
    return isCompleted;
  }

  public void setCompleted(boolean completed) {
    isCompleted = completed;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getFaultId() {
    return faultId;
  }

  public String getReporter() {
    return reporter;
  }

  public void setReporter(String reporter) {
    this.reporter = reporter;
  }

  public void setFaultId(String faultId) {
    this.faultId = faultId;
  }

  public long getPropId() {
    return propId;
  }

  public void setPropId(long propId) {
    this.propId = propId;
  }

  public String getReporterName() {
    return reporterName;
  }

  public void setReporterName(String reporterName) {
    this.reporterName = reporterName;
  }
}
