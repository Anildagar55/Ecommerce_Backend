package com.example.Eco_Backend.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "inventory")
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany
    @JoinColumn(name = "variant_id",nullable = false)
    private ProductVariant variant;

    private String warehouseId;
    @Column(nullable = false)
    private Integer quantity=0;
}
