package com.example.Eco_Backend.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShipmentResponse {
    private Long id;
    private Long orderId;
    private String courierPartner;
    private String trackingId;
    private String status;

}
