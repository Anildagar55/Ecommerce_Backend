package com.example.Eco_Backend.Repository;

import com.example.Eco_Backend.DTO.AddressResponse;
import com.example.Eco_Backend.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {
 List<Address>findByUserId(Long userId);
}
