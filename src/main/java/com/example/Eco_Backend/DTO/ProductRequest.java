package com.example.Eco_Backend.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequest {
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    @NotNull
    @Positive(message = "Base price must be positive")
    private Double basePrice;
    @NotNull(message = "Category Id is required")
    private Long categoryId;
    @NotNull(message = "Seller Id is required")
    private Long sellerId;

}
