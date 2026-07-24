package com.example.Eco_Backend.Repository;

import com.example.Eco_Backend.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
