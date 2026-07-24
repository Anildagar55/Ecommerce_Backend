package com.example.Eco_Backend.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
public class OrderRequest {
    @NotNull(message = "User Id is required")
    private Long userId;
    @NotNull(message = "Seller Id is required")
    private Long sellerId;
    @NotEmpty(message = "Order must have at least one item")
    @Valid
    private List<OrderItemRequest>items;
}
