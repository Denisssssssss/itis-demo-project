package com.technokratos.minimyini.util;

import com.technokratos.minimyini.dto.ReviewDto;
import com.technokratos.minimyini.model.Review;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewDto toDto(Review review);

    List<ReviewDto> toDtos(List<Review> reviews);

    Review toEntity(ReviewDto reviewDto);
}
