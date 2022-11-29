package com.fixer.lt.service;

import com.fixer.lt.entity.Landlord;
import com.fixer.lt.services.LandlordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class LandlordController {

  private LandlordService landlordService;

  @Autowired
  public LandlordController(LandlordService landlordService) {
    this.landlordService = landlordService;
  }

  @GetMapping("/landlord/{id}")
  public ResponseEntity<?> getLandlordAccount(@PathVariable long id) {
    Landlord createdLandlord = landlordService.findLandlordById(id);
    return new ResponseEntity<>(createdLandlord, HttpStatus.OK);
  }

  @PostMapping("/landlord")
  public ResponseEntity<?> updateLandlordAccountDetails(@RequestBody UpdateRequest updateRequest) {
    landlordService.updateLandlordAccount(updateRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}

