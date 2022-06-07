package com.technokratos.minimyini.controller;

import com.technokratos.minimyini.dto.HotelDto;
import com.technokratos.minimyini.dto.HotelsDto;
import com.technokratos.minimyini.dto.PriceDto;
import com.technokratos.minimyini.security.UserDetailsImpl;
import com.technokratos.minimyini.service.HotelService;
import com.technokratos.minimyini.util.HotelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    private final HotelMapper mapper;

    @PostMapping(value = "/hotels")
    public void addHotel(@RequestBody HotelDto hotelDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        hotelService.save(hotelDto, userDetails.getUser());
    }

    @PutMapping("/hotels/{hotelId}")
    public void updateHotel(@RequestBody HotelDto hotelDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        hotelService.save(hotelDto, userDetails.getUser());
    }

    @GetMapping(value = "/hotels")
    public List<HotelDto> getHotels() {
        return hotelService.findAll();
    }

    @GetMapping("/hotels/{id}")
    public HotelDto getHotel(@PathVariable Long id) {
        return mapper.toDto(hotelService.findById(id));
    }

    @GetMapping("/hotels/addresses/{addressId}")
    public HotelsDto getHotelsByAddress(@PathVariable Long addressId) {
        return hotelService.findByAddress(addressId);
    }

    @GetMapping("/hotels/{hotelId}/cheap")
    public PriceDto getCheap(@PathVariable Long hotelId) {
        return hotelService.getBestPriceByCriteria(true, hotelId);
    }

    @GetMapping("/hotels/{hotelId}/expensive")
    public PriceDto getExpensive(@PathVariable Long hotelId) {
        return hotelService.getBestPriceByCriteria(false, hotelId);
    }

    @GetMapping("/hotels/{hotelId}/expensive/global")
    public PriceDto getGlobalCheap(@PathVariable Long hotelId) {
        return hotelService.getGlobalBestPriceByCriteria(false, hotelId);
    }

    @GetMapping("/hotels/{hotelId}/cheap/global")
    public PriceDto getGlobalExpensive(@PathVariable Long hotelId) {
        return hotelService.getGlobalBestPriceByCriteria(true, hotelId);
    }

}
