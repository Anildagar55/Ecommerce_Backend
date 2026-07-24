package com.example.Eco_Backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private Long id;
    private Long orderId;
    private String method;
    private String status;
    private Double amount;
    private String gatewayIxnId;

}
