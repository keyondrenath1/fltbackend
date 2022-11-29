package com.fixer.lt.repository;

import com.fixer.lt.entity.Landlord;
import com.fixer.lt.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findAllByLandlord(Landlord landlord);
}