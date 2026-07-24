package com.example.Eco_Backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemsResponse {
    private Long id;
    private String productTitle;
    private String sku;
    private Integer quantity;
    private Double price;
}
