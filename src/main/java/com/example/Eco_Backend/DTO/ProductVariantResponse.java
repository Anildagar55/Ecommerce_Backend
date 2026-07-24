package com.example.Eco_Backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class ProductVariantResponse {
    private Long id;
    private String size;
    private String color;
    private Double price;
    private String sku;
    private Integer stockQuantity;

}
