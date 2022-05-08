package com.technokratos.minimyini.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@ApiModel(value = "Apartment info dto")
public class ApartmentDto {

    @ApiModelProperty(required = true)
    private final Long number;
    @ApiModelProperty(required = true)
    private final Boolean isLocked;
    @ApiModelProperty(required = true)
    private final Long roomsNumber;
    @ApiModelProperty(required = true)
    private final Long bedroomsNumber;
    @ApiModelProperty(required = true)
    private final List<Long> addonIdList;
}
