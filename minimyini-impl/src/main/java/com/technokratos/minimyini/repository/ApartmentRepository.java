package com.technokratos.minimyini.repository;

import com.technokratos.minimyini.model.Apartment;
import com.technokratos.minimyini.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    Optional<Apartment> findByNumberAndHotel(Long number, Hotel hotel);
}
