package com.fixer.lt.repository;

import com.fixer.lt.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
}