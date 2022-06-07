package com.technokratos.minimyini.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ReviewsDto {
    private List<ReviewDto> values;
}
