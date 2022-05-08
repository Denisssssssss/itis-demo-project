package com.technokratos.minimyini.service.impl;

import com.technokratos.minimyini.dto.ApartmentDto;
import com.technokratos.minimyini.exception.ApartmentCreationException;
import com.technokratos.minimyini.exception.ApartmentExistsException;
import com.technokratos.minimyini.exception.ApartmentModificationException;
import com.technokratos.minimyini.exception.ApartmentNotFoundException;
import com.technokratos.minimyini.model.Apartment;
import com.technokratos.minimyini.model.Hotel;
import com.technokratos.minimyini.repository.ApartmentRepository;
import com.technokratos.minimyini.service.ApartmentService;
import com.technokratos.minimyini.service.FacilityService;
import com.technokratos.minimyini.service.HotelService;
import com.technokratos.minimyini.util.ApartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final FacilityService facilityService;
    private final HotelService hotelService;
    private final ApartmentMapper apartmentMapper;

    @Override
    public Apartment save(ApartmentDto apartmentDto, Long hotelId, Long ownerId) {
        Hotel hotel = hotelService.findById(hotelId);
        if (!hotel.getOwner().getId().equals(ownerId)) {
            throw new ApartmentCreationException("Only owner can add apartments");
        }
        apartmentRepository.findByNumberAndHotel(apartmentDto.getNumber(), hotel)
                .ifPresent(x -> {
                    throw new ApartmentExistsException();
                });
        Apartment apartment = apartmentMapper.toEntity(apartmentDto);
        apartment.setFacilities(apartmentDto.getAddonIdList().stream()
                .map(facilityService::findById)
                .collect(Collectors.toList()));
        apartment.setHotel(hotelService.findById(hotelId));

        return apartmentRepository.save(apartment);
    }

    @Override
    public Apartment findById(Long id) {
        return apartmentRepository.findById(id).orElseThrow(ApartmentNotFoundException::new);
    }

    @Override
    public void closeForMaintenance(Long apartmentId, Long userId) {
        if (!findById(apartmentId).getHotel().getOwner().getId().equals(userId)) {
            throw new ApartmentModificationException("Only owner can modify apartments");
        }
        Apartment apartment = apartmentRepository.findById(apartmentId).orElseThrow(ApartmentNotFoundException::new);
        apartment.setIsLocked(true);
        apartmentRepository.save(apartment);
    }
}
