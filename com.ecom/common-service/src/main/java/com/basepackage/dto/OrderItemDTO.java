package com.basepackage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO {
    private String productId;
    private String productName;
    private String category;
    private double price;
    private String currency;
    private int quantity;
    private double subtotalAmount;

    private String sellerId;
    private String sellerName;
}