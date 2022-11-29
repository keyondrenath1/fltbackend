package com.fixer.lt.services;

import com.fixer.lt.PropertyRequest;
import com.fixer.lt.entity.Property;

import java.util.List;

public interface PropertyService {

  Property createProperty(PropertyRequest propertyRequest);
  List<Property> getProperties(long landlordId);
  Property findPropertyById(long id);
}
