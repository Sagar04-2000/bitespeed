package com.bite.bitespeed.response;
import com.bite.bitespeed.dto.Contact;

public class CustomerContactResponse {

    private Contact contact;

    private String exceptionMessage;

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}

