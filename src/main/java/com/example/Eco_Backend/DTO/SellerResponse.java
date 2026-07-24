package com.example.Eco_Backend.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SellerResponse {
    private Long id;
    private String business_name;
    private String gst_number;
    private Double rating;
    private String status;

}
