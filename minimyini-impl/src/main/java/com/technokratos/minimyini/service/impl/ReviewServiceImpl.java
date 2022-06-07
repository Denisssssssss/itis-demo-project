package com.technokratos.minimyini.service.impl;

import com.technokratos.minimyini.dto.ReviewDto;
import com.technokratos.minimyini.dto.ReviewsDto;
import com.technokratos.minimyini.model.Review;
import com.technokratos.minimyini.repository.ReviewRepository;
import com.technokratos.minimyini.service.HotelService;
import com.technokratos.minimyini.service.ReviewService;
import com.technokratos.minimyini.service.UserService;
import com.technokratos.minimyini.util.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final HotelService hotelService;

    private final UserService userService;

    private final ReviewMapper reviewMapper;

    @Override
    public Long save(ReviewDto reviewDto, Long authorId, Long hotelId) {
        Review review = reviewMapper.toEntity(reviewDto);
        review.setHotel(hotelService.findById(hotelId));
        review.setUser(userService.findById(authorId));

        return reviewRepository.save(review).getId();
    }

    @Override
    public ReviewsDto getByHotelId(Long hotelId) {
        return ReviewsDto.builder()
                .values(reviewMapper.toDtos(reviewRepository.findAllByHotelId(hotelId)))
                .build();
    }

    @Override
    public ReviewsDto getByAuthorId(Long authorId) {
        return ReviewsDto.builder()
                .values(reviewMapper.toDtos(reviewRepository.findAllByUserId(authorId)))
                .build();
    }
}
