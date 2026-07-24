package com.example.Eco_Backend.Repository;

import com.example.Eco_Backend.Entity.Address;
import com.example.Eco_Backend.Entity.Categaries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categaries,Long> {
}
