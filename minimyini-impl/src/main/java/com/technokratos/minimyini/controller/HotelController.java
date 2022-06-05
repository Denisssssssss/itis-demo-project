package com.technokratos.minimyini.controller;

import com.technokratos.minimyini.dto.HotelDto;
import com.technokratos.minimyini.security.UserDetailsImpl;
import com.technokratos.minimyini.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping(value = "/hotels")
    public void addHotel(@RequestBody HotelDto hotelDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        hotelService.save(hotelDto, userDetails.getUser());
    }

    @GetMapping(value = "/hotels")
    public List<HotelDto> getHotels() {
        return hotelService.findAll();
    }
}
