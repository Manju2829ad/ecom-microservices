package com.basepackage.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="shippinginfo")
public class ShippingInfo {

    private String id;

    private List<Address> address;

    // Seller info — add if relevant to shipping
    private String sellerId;
    private String sellerName;
    private String sellerContact;
    
}
