package com.example.Eco_Backend.Entity;

import com.example.Eco_Backend.Enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "seller_id",nullable = false)
    private Seller seller;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status=OrderStatus.PLACED;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(nullable = false)
    private LocalDateTime createdAt=LocalDateTime.now();

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItems>items;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private Payment payment;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private Shipment shipment;


}

