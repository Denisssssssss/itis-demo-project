package com.technokratos.minimyini.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewDto {

    private Integer rating;

    private String text;

    private Long hotelId;
}
