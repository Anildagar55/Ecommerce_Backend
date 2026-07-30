package com.example.Eco_Backend.Service;

import com.example.Eco_Backend.DTO.ReviewRequest;
import com.example.Eco_Backend.DTO.ReviewResponse;
import com.example.Eco_Backend.Entity.Product;
import com.example.Eco_Backend.Entity.Review;
import com.example.Eco_Backend.Entity.User;
import com.example.Eco_Backend.ExceptionHandler.ResourceNotFoundException;
import com.example.Eco_Backend.Repository.ProductRepository;
import com.example.Eco_Backend.Repository.ReviewRepository;
import com.example.Eco_Backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    @CacheEvict(value = "products", key = "#request.productId")
    public ReviewResponse addReview(ReviewRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + request.getUserId()));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + request.getProductId()));

        Review review = Review.builder()
                .user(user)
                .product(product)
                .rating(request.getRating())
                .comment(request.getComment())
                .build();
        return mapToResponse(reviewRepository.save(review));
    }

    public List<ReviewResponse> getReviewsByProduct(Long productId) {
        return reviewRepository.findByProductId(productId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ReviewResponse mapToResponse(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .userName(review.getUser().getName())
                .rating(review.getRating())
                .comment(review.getComment())
                .build();
    }
}
