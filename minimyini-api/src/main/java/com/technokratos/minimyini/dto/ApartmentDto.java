package com.technokratos.minimyini.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@ApiModel(value = "Apartment info dto")
public class ApartmentDto implements Comparable<ApartmentDto>{

    private final Long id;
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

    private final Long price;

    @Override
    public int compareTo(ApartmentDto o) {
        return Long.compare(this.getPrice(), o.getPrice());
    }
}
