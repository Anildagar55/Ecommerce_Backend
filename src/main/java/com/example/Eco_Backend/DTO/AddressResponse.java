package com.example.Eco_Backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class AddressResponse {
    private Long id;
    private String addressLine;
    private String city;
    private String pinCode;
    private boolean isDefault;
}
