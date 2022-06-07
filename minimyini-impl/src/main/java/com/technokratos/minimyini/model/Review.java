package com.technokratos.minimyini.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Review extends BaseEntity {

    private Integer rating;

    private String text;

    @ManyToOne(cascade = CascadeType.ALL)
    private Hotel hotel;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
