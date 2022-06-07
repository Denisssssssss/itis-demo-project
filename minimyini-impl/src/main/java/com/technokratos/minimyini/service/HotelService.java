package com.technokratos.minimyini.service;

import com.technokratos.minimyini.dto.HotelDto;
import com.technokratos.minimyini.dto.HotelsDto;
import com.technokratos.minimyini.dto.PriceDto;
import com.technokratos.minimyini.model.Hotel;
import com.technokratos.minimyini.model.User;

import java.util.List;

public interface HotelService {

    Hotel save(HotelDto hotelDto, User owner);

    void update(HotelDto hotelDto, User owner);

    Hotel findById(Long id);

    HotelsDto findByAddress(Long id);

    List<HotelDto> findAll();

    PriceDto getBestPriceByCriteria(boolean cheapest, Long hotelId);

    PriceDto getGlobalBestPriceByCriteria(boolean cheapest, Long hotelId);
}
