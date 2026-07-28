package com.example.Eco_Backend.Repository;

import com.example.Eco_Backend.DTO.AddressResponse;
import com.example.Eco_Backend.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
 List<Address>findByUserId(Long userId);
}
