package com.example.Eco_Backend.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductVariantRequest {
    @NotNull(message = "Product Id is required")
    private Long productId;
    private String size;
    private String color;
    @NotNull
    @Positive(message ="Price must be positive")
    private Double price;
    @NotBlank(message = "SKU is reuqired")
    private String sku;
}
