package com.basepackage.dto;

import lombok.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryDTO {
    private String id;
    private String productId; // foreign key to ProductCatalog
    private int quantity;     // current available stock
    
    // Optional enhancements
    private int reservedQuantity;   // quantity reserved but not yet shipped (optional)
    private String warehouseId;     // optional warehouse/location identifier
    private Instant lastUpdated;    // timestamp for last inventory update
}
