package com.example.Eco_Backend.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryResponse {
    private Long id;
    private String sku;
    private String warehouseId;
    private Integer quantity;
}
