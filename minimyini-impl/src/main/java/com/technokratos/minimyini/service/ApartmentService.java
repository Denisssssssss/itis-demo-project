package com.technokratos.minimyini.service;

import com.technokratos.minimyini.dto.ApartmentDto;
import com.technokratos.minimyini.model.Apartment;

public interface ApartmentService {

    Apartment save(ApartmentDto apartmentDto, Long hotelId, Long ownerId);

    Apartment findById(Long id);

    void closeForMaintenance(Long apartmentId, Long userId);
}
