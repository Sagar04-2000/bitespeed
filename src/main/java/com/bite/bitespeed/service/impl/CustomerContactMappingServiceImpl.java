package com.bite.bitespeed.service.impl;

import com.bite.bitespeed.dto.Contact;
import com.bite.bitespeed.entity.CustomerContactMapping;
import com.bite.bitespeed.entity.LinkPrecedence;
import com.bite.bitespeed.repository.CustomerContactMappingRepository;
import com.bite.bitespeed.request.CustomerContactRequest;
import com.bite.bitespeed.response.CustomerContactResponse;
import com.bite.bitespeed.service.CustomerContactMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerContactMappingServiceImpl implements CustomerContactMappingService {

    private static final Logger logger= LoggerFactory.getLogger("CUSTOMER_CONTAC_MAPPING_SERVICE_IMPL");
    private
    @Autowired
    CustomerContactMappingRepository customerContactMappingRepository;

    @Override
    public ResponseEntity<CustomerContactResponse> identifyCustomerContact(CustomerContactRequest customerContactRequest) {
        ResponseEntity<CustomerContactResponse> response=null;
        CustomerContactResponse res=new CustomerContactResponse();
        boolean hasExceptionOccured=false;
        HttpStatus httpStatus=HttpStatus.OK;
        String exceptionMessage=null;
        try{

            //to check contact is already present with email

            boolean isPresent=false;
            List<CustomerContactMapping> customerContactMappings=new ArrayList<>();
            // List<CustomerContactMapping> customerContactMappings=new ArrayList<>();
            CustomerContactMapping customerContactMapping=null;
            List<CustomerContactMapping> customerContactMappingList=customerContactMappingRepository.findByEmail(customerContactRequest.getEmail());
            if(customerContactMappingList!=null && customerContactMappingList.size()>0){
                //User present with Email;
                // customerContactMapping=customerContactMappingOptional.get();
                logger.info("User present with Email");
                customerContactMappings.addAll(customerContactMappingList);
                isPresent=true;
            }else{
                //User not present with email
                //search by phoneNumber;
                //logger.info("User present with phonenumber");
                List<CustomerContactMapping> customerContactMappingPhoneNumber=customerContactMappingRepository.findByPhoneNumber(customerContactRequest.getPhoneNumber());
                if(customerContactMappingPhoneNumber!=null && customerContactMappingPhoneNumber.size()>0){
                    //User Present with Phone Number
                    logger.info("User present with phonenumber");
                    customerContactMappings.addAll(customerContactMappingPhoneNumber);
                    isPresent=true;
                }


            }

            if(customerContactRequest.getEmail()!=null && customerContactRequest.getPhoneNumber()!=null) {
                logger.info("USER not present...creating new contact");
                customerContactMapping = new CustomerContactMapping();
                customerContactMapping.setEmail(customerContactRequest.getEmail());
                customerContactMapping.setPhoneNumber(customerContactRequest.getPhoneNumber());
                customerContactMapping.setLinkedId(null);
                if (!isPresent) {
                    customerContactMapping.setLinkPrecedence(LinkPrecedence.PRIMARY);
                } else {
                    customerContactMapping.setLinkPrecedence(LinkPrecedence.SECONDARY);
                }
                customerContactMapping.setCreatedAt(new Date(System.currentTimeMillis()));
                customerContactMapping.setUpdatedAt(new Date(System.currentTimeMillis()));

                customerContactMapping = customerContactMappingRepository.save(customerContactMapping);
                customerContactMappings.add(customerContactMapping);
            }
            Long primaryContactId=null;
            List<Long> secondaryContactIds=new ArrayList<>();
            if(customerContactMappings!=null){
                //find the primaryContact ID;
                primaryContactId=customerContactMappings.stream().sorted((c1,c2)->c1.getCreatedAt().compareTo(c2.getCreatedAt())).findFirst().get().getId();
                secondaryContactIds=customerContactMappings.stream().sorted((c1,c2)->c1.getCreatedAt().compareTo(c2.getCreatedAt())).filter(c->c.getLinkPrecedence().toString().equalsIgnoreCase("SECONDARY")).map(CustomerContactMapping::getId).collect(Collectors.toList());
            }else{
                primaryContactId=customerContactMapping.getId();
                secondaryContactIds=null;
            }

            Contact contact=new Contact();
            contact.setPrimaryContactId(primaryContactId);
            contact.setEmails(customerContactMappings.stream().sorted((c1,c2)->c1.getCreatedAt().compareTo(c2.getCreatedAt())).map(c1->c1.getEmail()).collect(Collectors.toSet()));
            contact.setPhoneNumbers(customerContactMappings.stream().sorted((c1,c2)->c1.getCreatedAt().compareTo(c2.getCreatedAt())).map(CustomerContactMapping::getPhoneNumber).collect(Collectors.toSet()));
            contact.setSecondaryContactIds(secondaryContactIds);

            res.setContact(contact);


        }catch(Exception e){
            hasExceptionOccured=true;
            exceptionMessage=e.getMessage();
            e.printStackTrace();

        }finally{
            if(hasExceptionOccured){
                httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
                res.setExceptionMessage(exceptionMessage);
            }

            response=new ResponseEntity<>(res,httpStatus);
        }
        return response;
    }


}

