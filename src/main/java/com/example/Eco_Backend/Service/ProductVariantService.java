package com.example.Eco_Backend.Service;

import com.example.Eco_Backend.DTO.ProductVariantRequest;
import com.example.Eco_Backend.DTO.ProductVariantResponse;
import com.example.Eco_Backend.Entity.Inventory;
import com.example.Eco_Backend.Entity.Product;
import com.example.Eco_Backend.Entity.ProductVariant;
import com.example.Eco_Backend.ExceptionHandler.ResourceNotFoundException;
import com.example.Eco_Backend.Repository.InventoryRepository;
import com.example.Eco_Backend.Repository.ProductRepository;
import com.example.Eco_Backend.Repository.ProductVariantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductVariantService {
    private final ProductVariantRepository variantRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    @Transactional
    public ProductVariantResponse createVariant(ProductVariantRequest request){
        Product product=productRepository.findById(request.getProductId())
                .orElseThrow(()->new ResourceNotFoundException("Product not found : "+request.getProductId()));
        ProductVariant variant=ProductVariant.builder()
                .product(product)
                .size(request.getSize())
                .color(request.getColor())
                .price(request.getPrice())
                .sku(request.getSku())
                .build();
        ProductVariant saved =variantRepository.save(variant);
        Inventory inventory =Inventory.builder()
                .variant(saved)
                .quantity(0)
                .build();
        inventoryRepository.save(inventory);
        return mapToResponse(saved,0);
    }

    public List<ProductVariantResponse> getVariantsByProduct(Long productId) {
        return variantRepository.findByProductId(productId).stream()
                .map(v -> {
                    int qty = inventoryRepository.findByVariantId(v.getId())
                            .map(Inventory::getQuantity).orElse(0);
                    return mapToResponse(v, qty);
                })
                .collect(Collectors.toList());
    }


    public ProductVariantResponse mapToResponse(ProductVariant variant ,int stockQty){
        return ProductVariantResponse.builder()
                .id(variant.getId())
                .size(variant.getSize())
                .color(variant.getColor())
                .price(variant.getPrice())
                .sku(variant.getSku())
                .stockQuantity(stockQty)
                .build();
}
}
