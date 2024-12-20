package com.travelagency.travel_agency.service;

import com.travelagency.travel_agency.model.Event;
import com.travelagency.travel_agency.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEventsByLocation(String location) {
        return eventRepository.findByLocation(location);
    }
}
