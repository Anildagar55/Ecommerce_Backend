package com.example.Eco_Backend.Controller;

import com.example.Eco_Backend.DTO.ProductRequest;
import com.example.Eco_Backend.DTO.ProductResponse;
import com.example.Eco_Backend.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductResponse>createProduct(@RequestBody ProductRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>updateProduct(@PathVariable Long id,@RequestBody ProductRequest request){
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<?>getProduct(Pageable pageable){
        return ResponseEntity.ok(productService.getActiveProducts(pageable));
    }
    @GetMapping("/search")
    public ResponseEntity<?>searchProduct(@RequestParam String keyword,Pageable pageable){
        return ResponseEntity.ok(productService.searchProducts(keyword, pageable));
    }
}
