package com.example.Eco_Backend.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShipmentRequest {
    @NotNull(message = "Order Id is required")
    private Long orderId;

    private String courierPartner;
    private String trackingId;

}
