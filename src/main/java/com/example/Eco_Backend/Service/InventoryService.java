package com.example.Eco_Backend.Service;

import com.example.Eco_Backend.DTO.InventoryResponse;
import com.example.Eco_Backend.Entity.Inventory;
import com.example.Eco_Backend.Repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;
    public InventoryResponse getByVariant(Long variantId){
       Inventory inventory=inventoryRepository.findByVariantId(variantId)
               .orElseThrow(()->new RuntimeException("Inventory not found for variant : "+variantId));
       return mapToResponse(inventory);
    }
    @Transactional
    public InventoryResponse updateStock(Long variantId,Integer quantity){
        Inventory inventory=inventoryRepository.findByVariantId(variantId)
                .orElseThrow(()->new RuntimeException("Inventory not found for variant : "+variantId));
        inventory.setQuantity(quantity);
        return mapToResponse(inventoryRepository.save(inventory));
    }
    public InventoryResponse mapToResponse(Inventory inventory){
        return InventoryResponse.builder()
                .id(inventory.getId())
                .sku(inventory.getVariant().getSku())
                .warehouseId(inventory.getWarehouseId())
                .quantity(inventory.getQuantity())
                .build();
    }
}
