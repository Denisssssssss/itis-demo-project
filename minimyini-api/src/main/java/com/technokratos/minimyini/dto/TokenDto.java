package com.technokratos.minimyini.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@ApiModel(value = "Access token dto")
@AllArgsConstructor
public class TokenDto implements Serializable {

    @ApiModelProperty(value = "Access token")
    private String token;
}
