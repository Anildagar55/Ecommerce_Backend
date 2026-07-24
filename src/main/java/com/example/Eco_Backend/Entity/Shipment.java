package com.example.Eco_Backend.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table(name = "shipments")
@Builder
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToMany
    @JoinColumn(name = "order_id",nullable = false,unique = true)
    private Order order;

    private String courierPartner;
    private String trackingId;

    @Column(nullable = false)
    private String status="PENDING";
}
