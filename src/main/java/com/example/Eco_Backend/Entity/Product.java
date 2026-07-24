package com.example.Eco_Backend.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "seller_id",nullable = false)
    Seller seller;

    @ManyToOne
    @JoinColumn(name = "categaries_id",nullable = false)
    Categaries categaries;

    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String description;
    @Column(nullable = false)
    double basePrice;
    @Column(nullable = false)
    String status="ACTIVE";

    @OneToMany(mappedBy = "product" ,cascade = CascadeType.ALL)
    List<Review>reviewList;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    List<ProductVariant>productVariantList;

}
