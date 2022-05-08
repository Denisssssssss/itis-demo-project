package com.technokratos.minimyini.service;

import com.technokratos.minimyini.dto.BookingDto;

public interface BookingService {

    BookingDto save(BookingDto bookingDto, Long userId);
}
