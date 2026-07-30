package com.example.Eco_Backend.Entity;

import com.example.Eco_Backend.Enums.PaymentMethod;
import com.example.Eco_Backend.Enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
         @ManyToOne
         @JoinColumn(name = "order_id",nullable = false,unique = true)
         Order order;

         @Enumerated(EnumType.STRING)
         @Column(nullable = false)
       private   PaymentMethod method;
         @Enumerated(EnumType.STRING)
         @Column(nullable = false)
      private    PaymentStatus status=PaymentStatus.PENDING;

         @Column(nullable = false)
       private   double amount;
         @Column(nullable = false)
  private   String gatewayTxnId;
    private LocalDateTime createdAt;

}
