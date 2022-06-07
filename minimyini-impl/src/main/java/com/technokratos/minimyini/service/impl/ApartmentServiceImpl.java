package com.technokratos.minimyini.service.impl;

import com.technokratos.minimyini.dto.ApartmentDto;
import com.technokratos.minimyini.dto.ApartmentsDto;
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

import java.util.List;
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
        if (hotel.getCheapest() > apartmentDto.getPrice()) {
            hotel.setCheapest(apartmentDto.getPrice());
        }
        if (hotel.getMostExpensive() < apartmentDto.getPrice()) {
            hotel.setMostExpensive(apartmentDto.getPrice());
        }
        apartmentRepository.findByNumberAndHotel(apartmentDto.getNumber(), hotel)
                .ifPresent(x -> {
                    throw new ApartmentExistsException();
                });
        Apartment apartment = apartmentMapper.toEntity(apartmentDto);
        if (apartmentDto.getAddonIdList() != null) {
            apartment.setFacilities(apartmentDto.getAddonIdList().stream()
                    .map(facilityService::findById)
                    .collect(Collectors.toList()));
        }
        apartment.setHotel(hotelService.findById(hotelId));

        return apartmentRepository.save(apartment);
    }

    @Override
    public Apartment findById(Long id) {
        return apartmentRepository.findById(id).orElseThrow(ApartmentNotFoundException::new);
    }

    @Override
    public ApartmentsDto getApartments(Long id) {
        return ApartmentsDto.builder()
                .values(apartmentMapper.toDtos(apartmentRepository.findAllByHotelId(id)))
                .build();
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

    @Override
    public List<ApartmentDto> getAllByHotelId(Long hotelId) {
        return apartmentMapper.toDtos(apartmentRepository.findAllByHotelId(hotelId));
    }

    @Override
    public List<ApartmentDto> getAll() {
        return apartmentMapper.toDtos(apartmentRepository.findAll());
    }
}
