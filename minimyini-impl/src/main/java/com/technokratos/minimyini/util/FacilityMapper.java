package com.technokratos.minimyini.util;

import com.technokratos.minimyini.dto.FacilityDto;
import com.technokratos.minimyini.model.Facility;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FacilityMapper {

    Facility toEntity(FacilityDto facilityDto);
}
