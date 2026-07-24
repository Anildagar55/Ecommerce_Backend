package com.example.Eco_Backend.Repository;

import com.example.Eco_Backend.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
