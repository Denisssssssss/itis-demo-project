package com.technokratos.minimyini.util;

import com.technokratos.minimyini.dto.BookingDto;
import com.technokratos.minimyini.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {

    Booking toEntity(BookingDto bookingDto);
}
