package com.example.Eco_Backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "sellers")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false ,unique = true)
    String business_name;
    @Column(nullable = false,unique = true)
    String gst_number;
    @Column(nullable = false)
    String bank_details;

    Double rating=0.0;

    String status="PENDING";

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Product>orderList;

}
