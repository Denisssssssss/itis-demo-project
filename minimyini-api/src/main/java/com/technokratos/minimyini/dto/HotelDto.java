package com.technokratos.minimyini.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Getter
@AllArgsConstructor
@ApiModel(value = "Hotel info dto")
public class HotelDto {

    private final Long id;
    @ApiModelProperty(required = true)
    private final LocalTime checkIn;
    @ApiModelProperty(required = true)
    private final LocalTime departure;
    @ApiModelProperty(required = true)
    private final String name;
    @ApiModelProperty(required = true)
    private final String email;
    @ApiModelProperty(required = true)
    private final String phoneNumber;

    private final Long cheapest;

    private final Long mostExpensive;

    private List<String> photos;

    @ApiModelProperty(required = true)
    private final AddressDto address;
}
