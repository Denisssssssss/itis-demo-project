package com.technokratos.minimyini.service.impl;

import com.technokratos.minimyini.dto.FacilityDto;
import com.technokratos.minimyini.exception.FacilityNotFoundException;
import com.technokratos.minimyini.model.Facility;
import com.technokratos.minimyini.repository.FacilityRepository;
import com.technokratos.minimyini.service.FacilityService;
import com.technokratos.minimyini.util.FacilityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;
    private final FacilityMapper facilityMapper;

    @Override
    public Facility save(FacilityDto facilityDto) {
        return facilityRepository.save(facilityMapper.toEntity(facilityDto));
    }

    @Override
    public Facility findById(Long id) {
        return facilityRepository.findById(id).orElseThrow(FacilityNotFoundException::new);
    }
}
