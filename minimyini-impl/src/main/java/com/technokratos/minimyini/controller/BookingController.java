package com.technokratos.minimyini.controller;

import com.technokratos.minimyini.dto.BookingDto;
import com.technokratos.minimyini.security.UserDetailsImpl;
import com.technokratos.minimyini.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping(value = "/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookingDto book(@AuthenticationPrincipal UserDetailsImpl userDetails,
                        @RequestBody BookingDto bookingDto) {
        return bookingService.save(bookingDto, userDetails.getUser().getId());
    }
}
