package com.technokratos.minimyini.model;

import com.technokratos.minimyini.dto.FacilityName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Facility extends BaseEntity {

    @Enumerated(value = EnumType.STRING)
    private FacilityName name;
    private Long price;

    @ManyToMany(mappedBy = "facilities")
    private List<Apartment> apartments;
}
