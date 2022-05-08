package com.technokratos.minimyini.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Apartment extends BaseEntity {

    private Long number;
    private Integer roomsNumber;
    private Integer bedroomsNumber;
    private Boolean isLocked;

    @ManyToMany
    @JoinTable(
            name = "apartment_facility",
            joinColumns = @JoinColumn(name = "apartment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id", referencedColumnName = "id")
    )
    private List<Facility> facilities;

    @ManyToOne
    private Hotel hotel;

    @OneToMany(mappedBy = "apartment")
    private List<Booking> bookings;
}
