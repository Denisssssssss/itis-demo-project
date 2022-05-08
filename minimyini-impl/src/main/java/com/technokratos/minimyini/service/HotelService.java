package com.technokratos.minimyini.service;

import com.technokratos.minimyini.dto.HotelDto;
import com.technokratos.minimyini.model.Hotel;
import com.technokratos.minimyini.model.User;

public interface HotelService {

    Hotel save(HotelDto hotelDto, User owner);

    Hotel findById(Long id);
}
