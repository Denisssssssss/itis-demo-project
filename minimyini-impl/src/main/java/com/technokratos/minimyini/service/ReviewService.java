package com.technokratos.minimyini.service;

import com.technokratos.minimyini.dto.ReviewDto;
import com.technokratos.minimyini.dto.ReviewsDto;

public interface ReviewService {

    Long save(ReviewDto reviewDto, Long authorId, Long hotelId);

    ReviewsDto getByHotelId(Long hotelId);

    ReviewsDto getByAuthorId(Long authorId);
}
