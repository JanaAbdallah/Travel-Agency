package com.travelagency.travel_agency.repository;

import com.travelagency.travel_agency.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByLocation(String location);
    List<Hotel> findAll();
}

