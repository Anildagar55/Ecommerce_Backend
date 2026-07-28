package com.example.Eco_Backend.Repository;

import com.example.Eco_Backend.Entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
public Optional<Inventory> findByVariantId(Long variantId);
}
