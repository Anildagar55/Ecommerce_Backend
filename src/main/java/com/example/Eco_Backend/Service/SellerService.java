package com.example.Eco_Backend.Service;

import com.example.Eco_Backend.DTO.SellerRequest;
import com.example.Eco_Backend.DTO.SellerResponse;
import com.example.Eco_Backend.Entity.Seller;
import com.example.Eco_Backend.ExceptionHandler.ResourceNotFoundException;
import com.example.Eco_Backend.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;
    public SellerResponse registerSeller(SellerRequest request){
        Seller seller=Seller.builder()
                .business_name(request.getBusiness_name())
                .bank_details(request.getBank_details())
                .gst_number(request.getGst_number())
                .rating(0.0)
                .status("PENDING") //admin approval dega
                .build();
      return mapToResponse(sellerRepository.save(seller));
    }
    public SellerResponse getSellerById(Long id){
        Seller seller=sellerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Seller not found : "+id));
        return mapToResponse(seller);
    }
    public List<SellerResponse>getAllSellers(){
        return sellerRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    public SellerResponse updateSellerStatus(Long id, String status){
        Seller seller=sellerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Seller not found : "+id));
        seller.setStatus(status);
        return mapToResponse(sellerRepository.save(seller));
    }
    public SellerResponse mapToResponse(Seller seller){
        return SellerResponse.builder()
                .id(seller.getId())
                .business_name(seller.getBusiness_name())
                .gst_number(seller.getGst_number())
                .rating(seller.getRating())
                .status(seller.getStatus())
                .build();
    }
}
