package com.technokratos.minimyini.controller;

import com.technokratos.minimyini.dto.HotelDto;
import com.technokratos.minimyini.security.UserDetailsImpl;
import com.technokratos.minimyini.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping(value = "/hotels")
    public void addHotel(@RequestBody HotelDto hotelDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        hotelService.save(hotelDto, userDetails.getUser());
    }
}
