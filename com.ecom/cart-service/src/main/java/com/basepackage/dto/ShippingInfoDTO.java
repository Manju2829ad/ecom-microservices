package com.basepackage.dto;

import java.util.List;

import lombok.Data;

@Data
public class ShippingInfoDTO {
    private String id;
    private List<AddressDTO> address;
}