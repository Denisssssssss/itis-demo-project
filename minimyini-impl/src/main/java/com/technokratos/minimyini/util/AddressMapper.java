package com.technokratos.minimyini.util;

import com.technokratos.minimyini.dto.AddressDto;
import com.technokratos.minimyini.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {

    Address toEntity(AddressDto addressDto);

    AddressDto toDto(Address address);

    List<AddressDto> toDtos(List<Address> addresses);
}
