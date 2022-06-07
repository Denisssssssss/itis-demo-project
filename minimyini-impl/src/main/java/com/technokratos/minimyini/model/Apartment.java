package com.technokratos.minimyini.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    private Long price;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Photo> photos;

    @ManyToMany
    @JoinTable(
            name = "apartment_facility",
            joinColumns = @JoinColumn(name = "apartment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id", referencedColumnName = "id")
    )
    private List<Facility> facilities;

    @ManyToOne(cascade = CascadeType.ALL)
    private Hotel hotel;

    @OneToMany(mappedBy = "apartment")
    private List<Booking> bookings;
}
