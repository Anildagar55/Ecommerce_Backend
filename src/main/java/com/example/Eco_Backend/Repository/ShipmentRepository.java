package com.example.Eco_Backend.Repository;

import com.example.Eco_Backend.Entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment,Long> {
Optional<Shipment>findByTrackingId(String trackingId);
}
