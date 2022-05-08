package com.technokratos.minimyini.controller;

import com.technokratos.minimyini.dto.FacilityDto;
import com.technokratos.minimyini.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;

    @PostMapping(value = "/facilities")
    public void addAddon(@RequestBody FacilityDto facilityDto) {
        facilityService.save(facilityDto);
    }
}
