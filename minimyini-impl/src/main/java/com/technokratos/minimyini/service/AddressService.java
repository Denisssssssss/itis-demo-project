package com.technokratos.minimyini.service;

import com.technokratos.minimyini.dto.AddressDto;
import com.technokratos.minimyini.dto.AddressesDto;
import com.technokratos.minimyini.model.Address;

public interface AddressService {

    Address save(AddressDto addressDto);

    AddressesDto getAll();
}
