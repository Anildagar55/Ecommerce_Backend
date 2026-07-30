package com.example.Eco_Backend.Controller;

import com.example.Eco_Backend.DTO.PaymentRequest;
import com.example.Eco_Backend.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @PostMapping("/create")
    public ResponseEntity<?>createPayment(@RequestBody PaymentRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.initatePayment(request));
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?>getByOrder(@PathVariable Long id){
        return ResponseEntity.ok(paymentService.getPaymentByOrder(id));
    }
    @GetMapping("/{gatewayTxnId}")
    public ResponseEntity<?>confirmPayment(@PathVariable String gatewayTxnId,@RequestParam boolean success){
        return ResponseEntity.ok(paymentService.confirmPayment(gatewayTxnId, success));
    }

}
