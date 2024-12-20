package com.travelagency.travel_agency.service;

import com.travelagency.travel_agency.model.Event;
import com.travelagency.travel_agency.model.User;
import com.travelagency.travel_agency.repository.EventRepository;
import com.travelagency.travel_agency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Event> getEventsByLocation(String location) {
        return eventRepository.findByLocation(location);
    }

    public Event addEvent(Event event) {
        if (event.getAvailableTickets() == 0) {
            event.setAvailableTickets(100); // Default value
        }
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }


    public String bookEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        event.decrementTickets();
        user.bookEvent(event);

        userRepository.save(user);
        eventRepository.save(event);

        return "User " + userId + " has successfully booked the event: " + event.getName();
    }


    public String cancelEventBooking(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getBookedEvents().contains(event)) {
            throw new RuntimeException("User has not booked this event");
        }

        user.cancelEvent(event);
        event.incrementTickets();

        userRepository.save(user);
        eventRepository.save(event);

        return "User " + userId + " has successfully canceled the booking for event: " + event.getName();
    }



}
