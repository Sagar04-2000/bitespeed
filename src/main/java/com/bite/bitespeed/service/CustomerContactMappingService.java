package com.bite.bitespeed.service;

import com.bite.bitespeed.request.CustomerContactRequest;
import com.bite.bitespeed.response.CustomerContactResponse;
import org.springframework.http.ResponseEntity;

public interface CustomerContactMappingService {
    ResponseEntity<CustomerContactResponse> identifyCustomerContact(CustomerContactRequest customerContactRequest);
}
