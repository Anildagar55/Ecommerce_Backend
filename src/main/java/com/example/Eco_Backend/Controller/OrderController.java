package com.example.Eco_Backend.Controller;

import com.example.Eco_Backend.DTO.OrderRequest;
import com.example.Eco_Backend.DTO.OrderResponse;
import com.example.Eco_Backend.Enums.OrderStatus;
import com.example.Eco_Backend.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/create")
    public ResponseEntity<OrderResponse>createOrder(@RequestBody OrderRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity <?> getByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.getOrderByUser(userId));
    }
    @GetMapping("/sellers/{sellerId}")
    public ResponseEntity<?>getBySellerId(@PathVariable Long sellerId){
        return ResponseEntity.ok(orderService.getOrderBySeller(sellerId));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?>updateOrder(@PathVariable Long id, @RequestParam OrderStatus status){
        return ResponseEntity.ok(orderService.updateOrderStatus(id,status));
    }
}
