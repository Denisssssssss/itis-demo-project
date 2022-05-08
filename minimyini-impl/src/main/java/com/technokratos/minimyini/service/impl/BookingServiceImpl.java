package com.technokratos.minimyini.service.impl;

import com.technokratos.minimyini.dto.BookingDto;
import com.technokratos.minimyini.exception.BookedApartmentException;
import com.technokratos.minimyini.model.Apartment;
import com.technokratos.minimyini.model.Booking;
import com.technokratos.minimyini.repository.BookingRepository;
import com.technokratos.minimyini.service.ApartmentService;
import com.technokratos.minimyini.service.BookingService;
import com.technokratos.minimyini.service.UserService;
import com.technokratos.minimyini.util.BookingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final ApartmentService apartmentService;
    private final BookingMapper bookingMapper;

    @Override
    public BookingDto save(BookingDto bookingDto, Long userId) {
        Booking booking = bookingMapper.toEntity(bookingDto);
        Apartment apartment = apartmentService.findById(bookingDto.getApartmentId());
        if (!validateTime(booking.getCheckIn(), booking.getDeparture(), apartment)) {
            throw new BookedApartmentException();
        }
        booking.setApartment(apartment);
        String code = generateCode();
        booking.setCode(code);
        booking.setUser(userService.findById(userId));
        bookingRepository.save(booking);
        bookingDto.setCode(code);

        return bookingDto;
    }

    private String generateCode() {
        Random random = new Random();
        return String.valueOf(random.nextInt(10))
                + random.nextInt(10)
                + random.nextInt(10)
                + random.nextInt(10);
    }

    private boolean validateTime(LocalDateTime checkIn, LocalDateTime departure, Apartment apartment) {
        List<Booking> bookings = apartment.getBookings().stream().sorted().collect(Collectors.toList());
        if (bookings.isEmpty()) {
            return true;
        }
        LocalDateTime firstCheckIn = bookings.stream()
                .map(Booking::getCheckIn)
                .sorted()
                .collect(Collectors.toList())
                .get(0);
        LocalDateTime lastDeparture = bookings.stream()
                .map(Booking::getDeparture)
                .sorted()
                .collect(Collectors.toList())
                .get(bookings.size() - 1);
        if (departure.isBefore(firstCheckIn) || checkIn.isAfter(lastDeparture)) {
            return true;
        }
        Booking prevBooking = bookings.get(0);
        for (Booking booking : bookings) {
            Booking availableBooking = prevBooking.getDifference(booking);
            if (availableBooking.getCheckIn().isBefore(checkIn) && availableBooking.getDeparture().isAfter(departure)) {
                return true;
            }
        }
        return false;
    }
}
