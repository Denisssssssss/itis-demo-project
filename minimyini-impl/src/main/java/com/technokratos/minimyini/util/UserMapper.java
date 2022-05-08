package com.technokratos.minimyini.util;

import com.technokratos.minimyini.dto.UserDto;
import com.technokratos.minimyini.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(source = "password", target = "hashPassword")
    User toEntity(UserDto userDto);
}
