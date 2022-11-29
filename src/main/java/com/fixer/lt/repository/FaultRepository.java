package com.fixer.lt.repository;

import com.fixer.lt.entity.Fault;
import com.fixer.lt.entity.Landlord;
import com.fixer.lt.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaultRepository extends JpaRepository<Fault, Long> {
    List<Fault> findAllByReporter(String reporter);
    Fault findByFaultId(String faultId);
}