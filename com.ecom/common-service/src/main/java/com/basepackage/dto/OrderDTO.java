package com.basepackage.dto;

import com.basepackage.OrderStatus;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private String orderId;
    private String userId;
    private List<OrderItemDTO> items;
    private Instant createdAt;
    private Instant updatedAt;
    private ShippingInfoDTO shippingInfo;
    private DiscountDTO discount;
    private OrderStatus status;
}
