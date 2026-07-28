package com.example.Eco_Backend.Repository;

import com.example.Eco_Backend.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
Page<Product>findByStatus(String status, Pageable pageable);
List<Product>findByCategoryId(Long categoryId);
List<Product>findBySellerId(Long sellerId);
Page<Product>findByTitleContainingIgnoreCase(String keyword,Pageable pageable);
}
