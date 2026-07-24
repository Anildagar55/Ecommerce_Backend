package com.example.Eco_Backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(nullable = false)
    String address_line;
    @Column(nullable = false)
    String city;
    @Column(nullable = false)
    String pincode;

    private boolean is_default=false;
}
