package com.example.Eco_Backend.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressRequest {
    @NotNull(message = "User Id is required")
    private Long userId;
    @NotBlank(message = "Address line is required")
    private String addressLine;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "PinCode is required")
    private String pinCode;

    private boolean isDefault;
}
