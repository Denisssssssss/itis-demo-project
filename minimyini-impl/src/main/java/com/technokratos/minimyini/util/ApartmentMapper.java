package com.technokratos.minimyini.util;

import com.technokratos.minimyini.dto.ApartmentDto;
import com.technokratos.minimyini.model.Apartment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApartmentMapper {

    Apartment toEntity(ApartmentDto apartmentDto);
}
