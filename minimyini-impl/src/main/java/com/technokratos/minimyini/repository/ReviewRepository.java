package com.technokratos.minimyini.repository;

import com.technokratos.minimyini.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByHotelId(Long hotelId);

    List<Review> findAllByUserId(Long userId);
}
