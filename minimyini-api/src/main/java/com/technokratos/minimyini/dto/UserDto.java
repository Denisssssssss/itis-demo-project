package com.technokratos.minimyini.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
@ApiModel(value = "User info dto")
public class UserDto {
    @ApiModelProperty(required = true)
    private String username;
    @ApiModelProperty(required = true)
    private String password;
    @ApiModelProperty(required = false)
    private String firstName;
    @ApiModelProperty(required = false)
    private String lastName;

    private Long telegramId;
}
