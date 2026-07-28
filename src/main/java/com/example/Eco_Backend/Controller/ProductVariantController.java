package com.example.Eco_Backend.Controller;

import com.example.Eco_Backend.DTO.ProductVariantRequest;
import com.example.Eco_Backend.DTO.ProductVariantResponse;
import com.example.Eco_Backend.Entity.ProductVariant;
import com.example.Eco_Backend.Service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/variant")
public class ProductVariantController {
    @Autowired
    ProductVariantService productVariantService;
    @PostMapping("/create")
    public ResponseEntity<?>createVariant(@RequestBody ProductVariantRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(productVariantService.createVariant(request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Long id){
        return ResponseEntity.ok(productVariantService.getVariantsByProduct(id));
    }

}
