package com.example.Eco_Backend.Service;

import com.example.Eco_Backend.DTO.ProductRequest;
import com.example.Eco_Backend.DTO.ProductResponse;
import com.example.Eco_Backend.Entity.Categaries;
import com.example.Eco_Backend.Entity.Product;
import com.example.Eco_Backend.Entity.Seller;
import com.example.Eco_Backend.Repository.ProductRepository;
import com.example.Eco_Backend.Repository.SellerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
         private final SellerRepository sellerRepository;
         @Transactional
    public ProductResponse createProduct(ProductRequest request){
             Seller seller=sellerRepository.findById(request.getSellerId())
                     .orElseThrow(()->new RuntimeException("Seller not found : "+request.getSellerId()));
             Product product=Product.builder()
                     .title(request.getTitle())
                     .description(request.getDescription())
                     .basePrice(request.getBasePrice())
                     .seller(seller)
                     .status("ACTIVE")
                     .build();
             Product saved=productRepository.save(product);
             return mapToResponse(saved);
         }
    @Cacheable(value = "products",key="#id")
    public ProductResponse getProductById(Long id){
             Product product=productRepository.findById(id)
                     .orElseThrow(()->new RuntimeException("Product id not found : "+id));
             return mapToResponse(product);
         }
         public ProductResponse mapToResponse(Product product){
             Categaries categaries=product.getCategaries();
             return ProductResponse.builder()
                     .id(product.getId())
                     .title(product.getTitle())
                     .description(product.getDescription())
                     .basePrice(product.getBasePrice())
                     .status(product.getStatus())
                     .sellerName(product.getSeller() !=null ? product.getSeller().getBusiness_name():null)
                     .categoryName(categaries !=null ? categaries.getName():null)
                     .build();

         }
         @Cacheable(value = "products", key = "'list_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<ProductResponse>getActiveProducts(Pageable pageable){
             return productRepository.findByStatus("ACTIVE",pageable)
                     .map(this::mapToResponse);
         }


         public Page<ProductResponse>searchProducts(String keyword,Pageable pageable){
             return productRepository.findByTitleContainingIgnoreCase(keyword,pageable)
                     .map(this::mapToResponse);
         }

         @Transactional
         @CacheEvict(value = "products", allEntries = true)
         public ProductResponse updateProduct(Long id,ProductRequest request){
             Product product=productRepository.findById(id)
                     .orElseThrow(()->new RuntimeException("Product not found with id: "+id));
           product.setTitle(request.getTitle());
           product.setDescription(request.getDescription());
           product.setBasePrice(request.getBasePrice());
           return mapToResponse(productRepository.save(product));
         }
         @Transactional
    public void deleteProduct(Long id){
             if (productRepository.existsById(id)){
                 throw new RuntimeException("Product not found with id : "+id);
             }
             productRepository.deleteById(id);
         }
}
