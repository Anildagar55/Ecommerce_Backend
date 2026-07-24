package com.example.Eco_Backend.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderItemRequest {
    @NotNull(message = "Variant Id is required")
    private Long variantId;
    @NotNull
    @Positive(message = "Quantity must be positive")
    private Integer quantity;
}
