package com.basepackage.model;

import lombok.Data;

@Data
public class Address {
    private String id;
    
    private String addresstype;
    
    private String addressName;
    private String recipientName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String phoneNumber;
    private String alternateNumber;
}