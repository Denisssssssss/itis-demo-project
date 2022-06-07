package com.technokratos.minimyini.controller;

import com.technokratos.minimyini.dto.ReviewsDto;
import com.technokratos.minimyini.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{hotelId}")
    public ReviewsDto getReviewsByHotelId(@PathVariable Long hotelId) {
        return reviewService.getByHotelId(hotelId);
    }
}
