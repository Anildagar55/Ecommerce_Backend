package com.example.Eco_Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "shipments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne
    @JoinColumn(name = "order_id",nullable = false,unique = true)
    private Order order;

    private String courierPartner;
    private String trackingId;

    @Column(nullable = false)
    private String status="PENDING";
}
