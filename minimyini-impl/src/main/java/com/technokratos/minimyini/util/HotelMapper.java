package com.technokratos.minimyini.util;

import com.technokratos.minimyini.dto.HotelDto;
import com.technokratos.minimyini.model.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {

    Hotel toEntity(HotelDto hotelDto);
}
