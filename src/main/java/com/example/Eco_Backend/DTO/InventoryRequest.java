package com.example.Eco_Backend.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class InventoryRequest {
    @NotNull(message = "Variant Id is required")
    private Long variantId;
    private String warehouseId;
    @NotNull
    @PositiveOrZero(message = "Quantity cannot be negative")
    private Integer quantity;
}
