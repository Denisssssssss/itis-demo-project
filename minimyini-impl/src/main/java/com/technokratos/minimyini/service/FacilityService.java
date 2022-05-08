package com.technokratos.minimyini.service;

import com.technokratos.minimyini.dto.FacilityDto;
import com.technokratos.minimyini.model.Facility;

public interface FacilityService {

    Facility save(FacilityDto facilityDto);

    Facility findById(Long id);
}
