package com.technokratos.minimyini.controller;

import com.technokratos.minimyini.dto.ReviewDto;
import com.technokratos.minimyini.dto.ReviewsDto;
import com.technokratos.minimyini.security.UserDetailsImpl;
import com.technokratos.minimyini.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{authorId}")
    public ReviewsDto getReviews(@PathVariable Long authorId) {
        return reviewService.getByAuthorId(authorId);
    }

    @PostMapping("/{hotelId}")
    public void addReview(@PathVariable Long hotelId, @RequestBody ReviewDto reviewDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reviewService.save(reviewDto, userDetails.getUser().getId(), hotelId);
    }

    @GetMapping("/{hotelId}")
    public ReviewsDto getReviewsByHotelId(@PathVariable Long hotelId) {
        return reviewService.getByHotelId(hotelId);
    }
}
