package com.bite.bitespeed.repository;

import com.bite.bitespeed.entity.CustomerContactMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerContactMappingRepository extends JpaRepository<CustomerContactMapping,Long> {
    List<CustomerContactMapping> findByPhoneNumber(String phoneNumber);

    List<CustomerContactMapping> findByEmail(String email);
}
