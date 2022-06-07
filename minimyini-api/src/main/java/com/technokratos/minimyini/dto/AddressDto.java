package com.technokratos.minimyini.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel(value = "Address info dto")
public class AddressDto {

    private final Long id;
    @ApiModelProperty(required = true)
    private final String postcode;
    @ApiModelProperty(required = true)
    private final String region;
    @ApiModelProperty(required = true)
    private final String city;
    @ApiModelProperty(required = true)
    private final String street;
    @ApiModelProperty(required = true)
    private final String buildingNumber;
}
