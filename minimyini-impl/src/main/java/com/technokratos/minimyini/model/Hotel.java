package com.technokratos.minimyini.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Hotel extends BaseEntity {

    private LocalTime checkIn;
    private LocalTime departure;
    private String name;
    private String email;
    private String phoneNumber;

    private Long cheapest;

    private Long mostExpensive;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Photo> photos;

    @ManyToOne
    private User owner;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "hotel")
    private List<Apartment> apartments;

    @OneToMany(mappedBy = "hotel")
    private List<Review> reviews;
}
