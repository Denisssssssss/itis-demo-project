package com.technokratos.minimyini.controller;

import com.technokratos.minimyini.dto.BookingDto;
import com.technokratos.minimyini.model.Booking;
import com.technokratos.minimyini.repository.BookingRepository;
import com.technokratos.minimyini.security.TokenProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookingControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    public void throws_exception_on_illegal_booking() {
        Booking booking = bookingRepository.findById(1L).get();
        BookingDto requestBody = BookingDto.builder()
                .apartmentId(booking.getApartment().getId())
                .checkIn(booking.getCheckIn())
                .departure(booking.getDeparture())
                .build();
        book(requestBody, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    @Sql(scripts = "classpath:/db/sql/data.sql")
    public void allows_to_book_free_time() {
        Booking booking = bookingRepository.findById(1L).get();
        BookingDto requestBody = BookingDto.builder()
                .apartmentId(booking.getApartment().getId())
                .checkIn(booking.getDeparture().plusDays(1))
                .departure(booking.getDeparture().plusDays(3))
                .build();
        book(requestBody, HttpStatus.OK);
    }

    public void book(BookingDto requestBody, HttpStatus httpStatus) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", tokenProvider.generate(1L));
        HttpEntity<?> entity = new HttpEntity<>(requestBody, headers);
        int status = restTemplate.postForEntity("/bookings", entity, BookingDto.class).getStatusCodeValue();
        Assertions.assertEquals(httpStatus.value(), status);
    }
}
