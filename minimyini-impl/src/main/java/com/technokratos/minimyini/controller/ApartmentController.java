package com.technokratos.minimyini.controller;

import com.technokratos.minimyini.dto.ApartmentDto;
import com.technokratos.minimyini.dto.ApartmentsDto;
import com.technokratos.minimyini.security.UserDetailsImpl;
import com.technokratos.minimyini.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApartmentController {

    private final ApartmentService apartmentService;

    @GetMapping("/apartments/{hotelId}")
    public ApartmentsDto getApartments(@PathVariable Long hotelId) {
        return apartmentService.getApartments(hotelId);
    }
    @PostMapping(value = "/apartments/{hotel-id}")
    public void addApartment(@RequestBody ApartmentDto apartmentDto,
                             @PathVariable("hotel-id") Long id,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        apartmentService.save(apartmentDto, id, userDetails.getUser().getId());
    }

    @PutMapping(value = "/apartments/{apartment-id}")
    public void closeForMaintenance(@PathVariable("apartment-id") Long id,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        apartmentService.closeForMaintenance(id, userDetails.getUser().getId());
    }
}
