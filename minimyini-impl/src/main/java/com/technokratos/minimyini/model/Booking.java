package com.technokratos.minimyini.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Booking extends BaseEntity implements Comparable<Booking> {

    @ManyToOne
    private User user;

    @ManyToOne
    private Apartment apartment;

    private String code;
    private LocalDateTime checkIn;
    private LocalDateTime departure;

    public Booking getDifference(Booking booking) {
        LocalDateTime maxCheckIn = this.checkIn.isAfter(booking.getCheckIn()) ?
                this.checkIn : booking.getCheckIn();
        LocalDateTime minDeparture = this.departure.isBefore(booking.getDeparture()) ?
                this.departure : booking.getDeparture();

        return Booking.builder()
                .checkIn(minDeparture)
                .departure(maxCheckIn)
                .build();
    }

    @Override
    public int compareTo(Booking booking) {
        if (!this.apartment.getId().equals(booking.getApartment().getId())) {
            throw new IllegalArgumentException();
        }
        if (this.checkIn.isBefore(booking.getCheckIn())) {
            return -1;
        } else if (this.checkIn.isAfter(booking.getCheckIn())) {
            return 1;
        } else {
            return 0;
        }
    }
}
