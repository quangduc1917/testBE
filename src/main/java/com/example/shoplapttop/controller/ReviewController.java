package com.example.shoplapttop.controller;

import com.example.shoplapttop.model.request.review.ReviewSectionRequest;
import com.example.shoplapttop.model.response.ApiResponse;
import com.example.shoplapttop.model.response.review.ReviewResponse;
import com.example.shoplapttop.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/api/review/add")
    @PreAuthorize("hasAnyRole('USER') OR hasAnyRole('ADMIN')")
    public ResponseEntity<?> reviewProduct(HttpServletRequest request, @RequestParam long productId,@RequestBody ReviewSectionRequest reviewSectionRequest){
        reviewService.insertReview(request,productId,reviewSectionRequest);
        return new ResponseEntity(new ApiResponse(true,"SUCCESS"), HttpStatus.OK);
    }

    @GetMapping("/api/public/review/all")
    public ResponseEntity<Page<ReviewResponse>> getAllReviews(@RequestParam int offset, @RequestParam int limit, @RequestParam long productId){
        return new ResponseEntity<>(reviewService.getAll(offset, limit, productId),HttpStatus.OK);
    }


}
