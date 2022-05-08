package com.technokratos.minimyini.model;

import lombok.*;

import javax.persistence.*;
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

    @ManyToOne
    private User owner;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "hotel")
    private List<Apartment> apartments;
}
