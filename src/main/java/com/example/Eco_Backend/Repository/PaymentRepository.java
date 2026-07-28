package com.example.Eco_Backend.Repository;

import com.example.Eco_Backend.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
Optional<Payment> findByGatewayTxnId(String gatewayTxnId);
Optional<Payment> findByOrderId(Long orderId);
}
