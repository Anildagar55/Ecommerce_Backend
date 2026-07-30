package com.example.Eco_Backend.Controller;

import com.example.Eco_Backend.DTO.ReviewRequest;
import com.example.Eco_Backend.DTO.ReviewResponse;
import com.example.Eco_Backend.Service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponse> add(@Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.addReview(request));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewResponse>> getByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.getReviewsByProduct(productId));
    }
}
