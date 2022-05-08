package com.technokratos.minimyini.service.impl;

import com.technokratos.minimyini.dto.HotelDto;
import com.technokratos.minimyini.exception.AddressIsTakenException;
import com.technokratos.minimyini.exception.HotelNotFoundException;
import com.technokratos.minimyini.model.Address;
import com.technokratos.minimyini.model.Hotel;
import com.technokratos.minimyini.model.User;
import com.technokratos.minimyini.repository.AddressRepository;
import com.technokratos.minimyini.repository.HotelRepository;
import com.technokratos.minimyini.service.HotelService;
import com.technokratos.minimyini.util.AddressMapper;
import com.technokratos.minimyini.util.HotelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final AddressRepository addressRepository;
    private final HotelMapper hotelMapper;
    private final AddressMapper addressMapper;

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
    public Hotel findById(Long id) {
        return hotelRepository.findById(id).orElseThrow(HotelNotFoundException::new);
    }
}
