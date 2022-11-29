package com.fixer.lt.repository;

import com.fixer.lt.entity.Account;
import com.fixer.lt.entity.Landlord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandlordRepository extends JpaRepository<Landlord, Long> {
    Landlord findLandlordByAccount(Account account);
}