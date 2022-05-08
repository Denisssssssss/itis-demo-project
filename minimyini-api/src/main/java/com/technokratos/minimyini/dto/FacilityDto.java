package com.technokratos.minimyini.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel(value = "Facility info dto")
public class FacilityDto {

    @ApiModelProperty(required = true)
    private final FacilityName name;
    @ApiModelProperty(required = false)
    private final Long price;
}

