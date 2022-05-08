package com.technokratos.minimyini.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity {

    private String postcode;
    private String region;
    private String city;
    private String street;
    private String buildingNumber;

    @OneToOne(mappedBy = "address")
    private Hotel hotel;
}
