package com.example.Eco_Backend.Controller;

import com.example.Eco_Backend.DTO.SellerRequest;
import com.example.Eco_Backend.DTO.SellerResponse;
import com.example.Eco_Backend.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;
    @PostMapping("/signup")
    public ResponseEntity<SellerResponse>registerSeller(@RequestBody SellerRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerService.registerSeller(request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Long id){
        return ResponseEntity.ok(sellerService.getSellerById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<?>getAllSeller(){
        return ResponseEntity.ok(sellerService.getAllSellers());
    }
    @PutMapping("/{id}/{status}")
    public ResponseEntity<?>updateSeller(@PathVariable Long id,@PathVariable String status){
        return ResponseEntity.ok(sellerService.updateSellerStatus(id,status));
    }
}
