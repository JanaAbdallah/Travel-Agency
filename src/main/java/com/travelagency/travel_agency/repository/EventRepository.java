package com.travelagency.travel_agency.repository;

import com.travelagency.travel_agency.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByLocation(String location);
    List<Event> findAll();
}
