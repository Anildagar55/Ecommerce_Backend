package com.example.Eco_Backend.Repository;

import com.example.Eco_Backend.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
      public Optional<Order>findByUserId(Long userId);
      public Optional<Order>findBySellerId(Long sellerId);
}
