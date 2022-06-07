package com.technokratos.minimyini.service.impl;

import com.technokratos.minimyini.dto.AddressDto;
import com.technokratos.minimyini.dto.AddressesDto;
import com.technokratos.minimyini.model.Address;
import com.technokratos.minimyini.repository.AddressRepository;
import com.technokratos.minimyini.service.AddressService;
import com.technokratos.minimyini.util.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper;

    @Override
    public Address save(AddressDto addressDto) {
        return addressRepository.save(addressMapper.toEntity(addressDto));
    }

    @Override
    public AddressesDto getAll() {
        return AddressesDto.builder()
                .values(addressMapper.toDtos(addressRepository.findAll()))
                .build();
    }
}
