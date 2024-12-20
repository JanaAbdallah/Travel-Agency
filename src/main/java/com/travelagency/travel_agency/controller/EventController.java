package com.travelagency.travel_agency.controller;

import com.travelagency.travel_agency.model.Event;
import com.travelagency.travel_agency.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/search")
    public ResponseEntity<List<Event>> getEvents(@RequestParam String location) {
        return ResponseEntity.ok(eventService.getEventsByLocation(location));
    }

    @PostMapping("/add")
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        Event addedEvent = eventService.addEvent(event);
        return ResponseEntity.ok(addedEvent);
    }


    @PostMapping("/book/{eventId}")
    public ResponseEntity<String> bookEvent(@PathVariable Long eventId, @RequestParam Long userId) {
        return ResponseEntity.ok(eventService.bookEvent(eventId, userId));
    }

    @PostMapping("/cancel/{eventId}")
    public ResponseEntity<String> cancelEventBooking(@PathVariable Long eventId, @RequestParam Long userId) {
        return ResponseEntity.ok(eventService.cancelEventBooking(eventId, userId));
    }
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

}
