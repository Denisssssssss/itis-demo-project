package com.technokratos.minimyini.repository;

import com.technokratos.minimyini.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "select address from Address address where " +
            "address.postcode = :#{#addressR.postcode} and " +
            "address.region = :#{#addressR.region} and " +
            "address.city = :#{#addressR.city} and " +
            "address.street = :#{#addressR.street} and " +
            "address.buildingNumber = :#{#addressR.buildingNumber}")
    Optional<Address> findAddress(@Param("addressR") Address addressR);
}
