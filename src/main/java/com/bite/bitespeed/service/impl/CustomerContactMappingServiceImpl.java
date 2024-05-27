package com.bite.bitespeed.service.impl;

import com.bite.bitespeed.request.CustomerContactRequest;
import com.bite.bitespeed.response.CustomerContactResponse;
import com.bite.bitespeed.service.CustomerContactMappingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerContactMappingServiceImpl implements CustomerContactMappingService {


    @Override
    public ResponseEntity<CustomerContactResponse> identifyCustomerContact(CustomerContactRequest customerContactRequest) {
        return null;
    }
}
