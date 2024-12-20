package com.travelagency.travel_agency.controller;

import com.travelagency.travel_agency.model.Hotel;
import com.travelagency.travel_agency.model.Recommendation;
import com.travelagency.travel_agency.service.HotelService;
import com.travelagency.travel_agency.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/search")
    public ResponseEntity<List<Hotel>> searchHotels(@RequestParam String location) {
        return ResponseEntity.ok(hotelService.searchHotelsByLocation(location));
    }

    @PostMapping("/book/{hotelId}")
    public ResponseEntity<Map<String, Object>> bookRoom(
            @PathVariable Long hotelId,
            @RequestParam Long userId,
            @RequestParam String roomType) {
        Hotel bookedHotel = hotelService.bookRoom(hotelId, userId, roomType);

        // Get recommendations for the city
        List<Recommendation> recommendations = recommendationService.getRecommendationsByLocation(bookedHotel.getLocation());

        // Prepare the response
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Room booked successfully!");
        response.put("hotel", bookedHotel);
        response.put("recommendations", recommendations);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
        try {
            Hotel addedHotel = hotelService.addHotel(hotel);
            return ResponseEntity.ok(addedHotel);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @PostMapping("/cancel/{hotelId}")
    public ResponseEntity<String> cancelRoomBooking(
            @PathVariable Long hotelId,
            @RequestParam Long userId,
            @RequestParam String roomType) {
        String message = hotelService.cancelRoomBooking(hotelId, userId, roomType);
        return ResponseEntity.ok(message);
    }

}
