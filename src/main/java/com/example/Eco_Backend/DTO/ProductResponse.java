package com.example.Eco_Backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String title;
    private String description;
    private Double basePrice;
    private String status;
    private String  categoryName;
    private String sellerName;
}
