package com.example.Eco_Backend.Controller;

import com.example.Eco_Backend.Entity.Inventory;
import com.example.Eco_Backend.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;
    @GetMapping("/{id}")
    public ResponseEntity<?>getByVariant(@PathVariable Long id){
        return ResponseEntity.ok(inventoryService.getByVariant(id));
    }
    @PutMapping("/{variantId}")
    public ResponseEntity<?>updateStock(@PathVariable Long variantId,@RequestParam Integer quantity){
        return ResponseEntity.ok(inventoryService.updateStock(variantId,quantity));
    }
}
