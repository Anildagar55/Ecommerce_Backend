package com.example.Eco_Backend.Repository;

import com.example.Eco_Backend.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
      public List<Order> findByUserId(Long userId);
      public List<Order>findBySellerId(Long sellerId);
      long countByOrderNumberStartingWith(String prefix);

}
