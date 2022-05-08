package com.technokratos.minimyini.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Facility info dto")
public class BookingDto {

    @ApiModelProperty(required = true)
    private Long apartmentId;
    @ApiModelProperty(required = true)
    private LocalDateTime checkIn;
    @ApiModelProperty(required = true)
    private LocalDateTime departure;
    @ApiModelProperty(required = false, value = "Code returns in response")
    private String code;
}
