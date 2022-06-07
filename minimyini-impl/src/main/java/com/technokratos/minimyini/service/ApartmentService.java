package com.technokratos.minimyini.service;

import com.technokratos.minimyini.dto.ApartmentDto;
import com.technokratos.minimyini.dto.ApartmentsDto;
import com.technokratos.minimyini.model.Apartment;

import java.util.List;

public interface ApartmentService {

    Apartment save(ApartmentDto apartmentDto, Long hotelId, Long ownerId);

    Apartment findById(Long id);

    ApartmentsDto getApartments(Long id);

    void closeForMaintenance(Long apartmentId, Long userId);

    List<ApartmentDto> getAllByHotelId(Long hotelId);

    List<ApartmentDto> getAll();
}
