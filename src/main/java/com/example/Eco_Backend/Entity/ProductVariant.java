package com.example.Eco_Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "product_variants")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
    @JoinColumn(name="product_id",nullable = false)
    private Product product;

private String size;
private String color;
 @Column(nullable = false)
    private Double price;
 @Column(nullable = false)
    private String sku;
}
