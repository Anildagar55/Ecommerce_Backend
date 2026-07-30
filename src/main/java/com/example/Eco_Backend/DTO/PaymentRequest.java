package com.example.Eco_Backend.DTO;

import com.example.Eco_Backend.Enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {
    @NotNull(message = "Order Id id required")
    private Long orderId;
@NotNull(message ="Payment method is required")
    private PaymentMethod method;
}
