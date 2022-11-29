package com.fixer.lt.services;

import com.fixer.lt.service.UpdateRequest;
import com.fixer.lt.entity.Landlord;

public interface LandlordService {
  Landlord findLandlordById(long id);
  void updateLandlordAccount(UpdateRequest updateRequest);
}
