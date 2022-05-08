package com.technokratos.minimyini.service.impl;

import com.technokratos.minimyini.exception.BookingNotFoundException;
import com.technokratos.minimyini.exception.CodeExpiredException;
import com.technokratos.minimyini.model.Booking;
import com.technokratos.minimyini.repository.BookingRepository;
import com.technokratos.minimyini.service.ApartmentService;
import com.technokratos.minimyini.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {

    private final BookingRepository bookingRepository;
    private final ApartmentService apartmentService;

    @Override
    public void useCode(String code, Long apartmentId) {
        Booking booking = bookingRepository
                .findByCodeAndApartment(code, apartmentService.findById(apartmentId))
                .orElseThrow(BookingNotFoundException::new);
        if (!validate(booking)) {
            throw new CodeExpiredException();
        }
    }

    private boolean validate(Booking booking) {
        LocalDateTime now = LocalDateTime.now();
        return booking.getCheckIn().isBefore(now) && booking.getDeparture().isAfter(now);
    }
}
