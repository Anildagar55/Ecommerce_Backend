package com.example.Eco_Backend.Repository;

import com.example.Eco_Backend.Entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
public Optional<Inventory> findByVariantId(Long variantId);
}
