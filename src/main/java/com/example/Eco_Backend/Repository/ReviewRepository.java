package com.example.Eco_Backend.Repository;

import com.example.Eco_Backend.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByProductId(Long productId);
    List<Review> findByUserId(Long userId);
}
