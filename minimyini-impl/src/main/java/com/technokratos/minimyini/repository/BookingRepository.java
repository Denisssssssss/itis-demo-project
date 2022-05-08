package com.technokratos.minimyini.repository;

import com.technokratos.minimyini.model.Apartment;
import com.technokratos.minimyini.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByCodeAndApartment(String code, Apartment apartment);
}
