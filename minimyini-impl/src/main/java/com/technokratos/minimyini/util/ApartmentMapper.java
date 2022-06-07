package com.technokratos.minimyini.util;

import com.technokratos.minimyini.dto.ApartmentDto;
import com.technokratos.minimyini.model.Apartment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApartmentMapper {

    Apartment toEntity(ApartmentDto apartmentDto);

    ApartmentDto toDto(Apartment apartment);

    List<ApartmentDto> toDtos(List<Apartment> apartments);
}
