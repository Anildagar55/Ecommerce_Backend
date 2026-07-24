package com.example.Eco_Backend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SellerRequest {
    @NotBlank(message = "business name is required")
    private String business_name;
    @NotBlank(message = "GST number is request")
    private String gst_number;
    private String bank_details;
}
