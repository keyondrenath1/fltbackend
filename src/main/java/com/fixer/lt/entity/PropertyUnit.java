package com.fixer.lt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class Account which maps to account table in the database via JPA ORM tool {Research for
 * Report}
 */
@Entity
public class PropertyUnit {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private boolean patio;
  private boolean garden;
  private boolean garage;
  private boolean storage;
  private int floors;
  private boolean livingRoom;
  private int bathroom;
  private int bedroom;
  private boolean office;
  private boolean fireplace;
  private boolean kitchen;
  private boolean basement;
  private boolean loft;
  private String propertyDescription;


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

  public boolean isPatio() {
    return patio;
  }

  public void setPatio(boolean patio) {
    this.patio = patio;
  }

  public boolean isGarden() {
    return garden;
  }

  public void setGarden(boolean garden) {
    this.garden = garden;
  }

  public boolean isGarage() {
    return garage;
  }

  public void setGarage(boolean garage) {
    this.garage = garage;
  }

  public boolean isStorage() {
    return storage;
  }

  public void setStorage(boolean storage) {
    this.storage = storage;
  }

  public int getFloors() {
    return floors;
  }

  public void setFloors(int floors) {
    this.floors = floors;
  }

  public boolean isLivingRoom() {
    return livingRoom;
  }

  public void setLivingRoom(boolean livingRoom) {
    this.livingRoom = livingRoom;
  }

  public int getBathroom() {
    return bathroom;
  }

  public void setBathroom(int bathroom) {
    this.bathroom = bathroom;
  }

  public int getBedroom() {
    return bedroom;
  }

  public void setBedroom(int bedroom) {
    this.bedroom = bedroom;
  }

  public boolean isOffice() {
    return office;
  }

  public void setOffice(boolean office) {
    this.office = office;
  }

  public boolean isFireplace() {
    return fireplace;
  }

  public void setFireplace(boolean fireplace) {
    this.fireplace = fireplace;
  }

  public boolean isKitchen() {
    return kitchen;
  }

  public void setKitchen(boolean kitchen) {
    this.kitchen = kitchen;
  }

  public boolean isBasement() {
    return basement;
  }

  public void setBasement(boolean basement) {
    this.basement = basement;
  }

  public boolean isLoft() {
    return loft;
  }

  public void setLoft(boolean loft) {
    this.loft = loft;
  }

  public String getPropertyDescription() {
    return propertyDescription;
  }

  public void setPropertyDescription(String propertyDescription) {
    this.propertyDescription = propertyDescription;
  }
}
