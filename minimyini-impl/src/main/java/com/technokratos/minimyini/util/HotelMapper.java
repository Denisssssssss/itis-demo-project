package com.technokratos.minimyini.util;

import com.technokratos.minimyini.dto.HotelDto;
import com.technokratos.minimyini.model.Hotel;
import com.technokratos.minimyini.model.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {

    Hotel toEntity(HotelDto hotelDto);

    List<HotelDto> toDtos(List<Hotel> hotelList);

    HotelDto toDto(Hotel hotel);

    default List<Photo> mapToPhotos(List<String> urls) {
        return urls.stream().map(url -> Photo.builder().url(url).build()).collect(Collectors.toList());
    }

    default List<String> mapToUrls(List<Photo> photos) {
        return photos.stream().map(Photo::getUrl).collect(Collectors.toList());
    }
}
