package com.basepackage.dto;



import java.math.BigDecimal;

import lombok.Data;

@Data
public class RequisitionItemDTO {

    private String itemDescription;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;
}
