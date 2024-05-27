package com.bite.bitespeed.controller;


import com.bite.bitespeed.request.CustomerContactRequest;
import com.bite.bitespeed.response.CustomerContactResponse;
import com.bite.bitespeed.service.CustomerContactMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/customer")
@RestController
public class CustomerController {

    private static final Logger logger= LoggerFactory.getLogger("CUSTOMER_CONTROLLER");
    private CustomerContactMappingService customerContactMappingService;

    @Autowired
    public void setCustomerContactMappingService(CustomerContactMappingService customerContactMappingService){
        this.customerContactMappingService=customerContactMappingService;
    }

    @PostMapping("/identify")
    public ResponseEntity<CustomerContactResponse> identifyCustomerContact(@RequestBody CustomerContactRequest customerContactRequest){
        logger.info("CUSTOMER_CONTROLLER_STARTED >>>");
        return customerContactMappingService.identifyCustomerContact(customerContactRequest);
    }
}

