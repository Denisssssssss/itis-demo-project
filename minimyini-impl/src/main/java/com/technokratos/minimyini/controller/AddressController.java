package com.technokratos.minimyini.controller;

import com.technokratos.minimyini.dto.AddressesDto;
import com.technokratos.minimyini.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/addresses")
    public AddressesDto getAddresses() {
        return addressService.getAll();
    }
}
