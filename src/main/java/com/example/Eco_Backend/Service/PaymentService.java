package com.example.Eco_Backend.Service;

import com.example.Eco_Backend.DTO.PaymentRequest;
import com.example.Eco_Backend.DTO.PaymentResponse;
import com.example.Eco_Backend.Entity.Order;
import com.example.Eco_Backend.Entity.Payment;
import com.example.Eco_Backend.Enums.PaymentStatus;
import com.example.Eco_Backend.Repository.OrderRepository;
import com.example.Eco_Backend.Repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private  final OrderRepository orderRepository;

    @Transactional
    public PaymentResponse initatePayment(PaymentRequest request){
        Order order=orderRepository.findById(request.getOrderId())
                .orElseThrow(()->new RuntimeException("Order not found : "+request.getOrderId()));
        Payment payment=Payment.builder()
                .order(order)
                .method(request.getMethod())
                .status(PaymentStatus.PENDING)
                .amount(order.getTotalAmount())
                .gatewayTxnId(request.getGatewayTxnId())
                .build();
        return mapToResponse(paymentRepository.save(payment));
    }
    @Transactional
    public PaymentResponse confirmPayment(String gatewayTxnId,boolean success){
        Payment payment=paymentRepository.findByGatewayTxnId(gatewayTxnId)
                .orElseThrow(()->new RuntimeException("Payment not found for txn : "+gatewayTxnId));
        payment.setStatus(success ? PaymentStatus.SUCCESS:PaymentStatus.FAILED);
        return mapToResponse(paymentRepository.save(payment));
    }
    public PaymentResponse getPaymentByOrder(Long orderId){
        Payment payment= paymentRepository.findByOrderId(orderId)
                .orElseThrow(()->new RuntimeException("Payment not found for order : "+orderId));
        return mapToResponse(payment);
    }
    public PaymentResponse mapToResponse(Payment payment){
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(payment.getOrder().getId())
                .method(payment.getMethod().name())
                .status(payment.getStatus().name())
                .amount(payment.getAmount())
                .gatewayIxnId(payment.getGatewayTxnId())
                .build();
    }
}
