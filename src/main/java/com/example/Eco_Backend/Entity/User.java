package com.example.Eco_Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
     String name;

    @Column(nullable = false, unique = true)
    String email;
    @Column(unique = true)
    String phone;
    @Column(nullable = false)
    String password_hash;
    @Column(nullable = false)
    LocalDateTime created_at=LocalDateTime.now();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Address>addressList;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL ,orphanRemoval = true)
    List<Order>orderList;

}
