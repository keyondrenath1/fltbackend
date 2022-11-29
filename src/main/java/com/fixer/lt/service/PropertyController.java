package com.fixer.lt.service;

import com.fixer.lt.PropertyRequest;
import com.fixer.lt.entity.Property;
import com.fixer.lt.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class PropertyController {

  private PropertyService propertyService;

  @Autowired
  public PropertyController(PropertyService propertyService) {
    this.propertyService = propertyService;
  }

  @PostMapping("/property")
  public ResponseEntity<?> createProperty(@RequestBody PropertyRequest propertyRequest) {
    Property createdProperty = propertyService.createProperty(propertyRequest);
    return new ResponseEntity<>(createdProperty, HttpStatus.CREATED);
  }

  @GetMapping("/property/{landlordId}")
  public ResponseEntity<?> getProperties(@PathVariable long landlordId) {
    List<Property> properties = propertyService.getProperties(landlordId);
    return new ResponseEntity<>(properties, HttpStatus.OK);
  }
}
