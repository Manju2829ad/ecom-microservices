package com.basepackage.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class DiscountDTO {
    private String id;
    private double discountAmount;
    private double discountPercent;
    private Instant validFrom;
    private Instant validTill;
    private boolean active;

}