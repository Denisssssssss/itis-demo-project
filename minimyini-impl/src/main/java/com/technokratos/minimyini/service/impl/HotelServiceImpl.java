package com.technokratos.minimyini.service.impl;

import com.technokratos.minimyini.dto.HotelDto;
import com.technokratos.minimyini.dto.HotelsDto;
import com.technokratos.minimyini.dto.PriceDto;
import com.technokratos.minimyini.exception.AddressIsTakenException;
import com.technokratos.minimyini.exception.HotelNotFoundException;
import com.technokratos.minimyini.model.Address;
import com.technokratos.minimyini.model.Apartment;
import com.technokratos.minimyini.model.Hotel;
import com.technokratos.minimyini.model.Photo;
import com.technokratos.minimyini.model.User;
import com.technokratos.minimyini.repository.AddressRepository;
import com.technokratos.minimyini.repository.ApartmentRepository;
import com.technokratos.minimyini.repository.HotelRepository;
import com.technokratos.minimyini.service.HotelService;
import com.technokratos.minimyini.util.AddressMapper;
import com.technokratos.minimyini.util.HotelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final AddressRepository addressRepository;
    private final HotelMapper hotelMapper;
    private final AddressMapper addressMapper;

    private final ApartmentRepository apartmentRepository;

    @Override
    public Hotel save(HotelDto hotelDto, User owner) {
        Address address = addressMapper.toEntity(hotelDto.getAddress());
        addressRepository.findAddress(address).ifPresent(x -> {
            throw new AddressIsTakenException();
        });
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        hotel.setOwner(owner);
        hotel.setAddress(address);

        return hotelRepository.save(hotel);
    }

    @Override
    public void update(HotelDto hotelDto, User owner) {
        Hotel hotel = hotelRepository.findById(hotelDto.getId()).orElseThrow(EntityNotFoundException::new);
        hotel.setName(hotelDto.getName());
        hotel.setEmail(hotelDto.getEmail());
        hotel.getPhotos().addAll(hotelDto.getPhotos().stream().map(photo -> Photo.builder().url(photo).build()).collect(Collectors.toList()));
        hotel.setPhoneNumber(hotelDto.getPhoneNumber());
        hotelRepository.save(hotel);
    }

    @Override
    public Hotel findById(Long id) {
        return hotelRepository.findById(id).orElseThrow(HotelNotFoundException::new);
    }

    @Override
    public HotelsDto findByAddress(Long id) {
        return HotelsDto.builder()
                .values(hotelMapper.toDtos(hotelRepository.findAllByAddressId(id)))
                .build();
    }

    @Override
    public List<HotelDto> findAll() {
        return hotelRepository.findAll().stream().map(hotelMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PriceDto getBestPriceByCriteria(boolean cheapest, Long hotelId) {
        LongStream longStream = apartmentRepository.findAllByHotelId((hotelId)).stream()
                .mapToLong(Apartment::getPrice);
        if (cheapest) {
            return PriceDto.builder()
                    .price(longStream.min().orElse(-1L))
                    .build();
        } else return PriceDto.builder()
                .price(longStream.max().orElse(-1L))
                .build();
    }

    @Override
    public PriceDto getGlobalBestPriceByCriteria(boolean cheapest, Long hotelId) {
        LongStream longStream = apartmentRepository.findAll().stream().mapToLong(Apartment::getPrice);
        if (cheapest) {
            return PriceDto.builder()
                    .price(longStream.min().orElse(-1L))
                    .build();
        } else return PriceDto.builder()
                .price(longStream.max().orElse(-1L))
                .build();
    }
}
