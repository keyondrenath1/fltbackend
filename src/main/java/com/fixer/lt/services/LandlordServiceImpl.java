package com.fixer.lt.services;

import com.fixer.lt.service.UpdateRequest;
import com.fixer.lt.entity.Account;
import com.fixer.lt.entity.Landlord;
import com.fixer.lt.repository.AuthenticationRepository;
import com.fixer.lt.repository.LandlordRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LandlordServiceImpl implements LandlordService {

  private LandlordRepository landlordRepository;
  private AuthenticationRepository authenticationRepository;

  @Autowired
  public LandlordServiceImpl(LandlordRepository landlordRepository, AuthenticationRepository authenticationRepository) {
    this.landlordRepository = landlordRepository;
    this.authenticationRepository = authenticationRepository;
  }

  @Override
  public Landlord findLandlordById(long id) {
    Optional<Landlord> optionalLandlord = landlordRepository.findById(id);
    return optionalLandlord.get();
  }

  @Override
  public void updateLandlordAccount(UpdateRequest updateRequest) {
    Optional<Landlord> optionalLandlord = landlordRepository.findById(updateRequest.getAccountId());
    Landlord landlord1 = optionalLandlord.get();
    landlord1.setFirst_name(updateRequest.getFirst_name());
    landlord1.setLast_name(updateRequest.getLast_name());
    landlordRepository.save(landlord1);

    Account account = landlord1.getAccount();
    account.setEmail(updateRequest.getEmail());
    authenticationRepository.save(account);
  }
}
