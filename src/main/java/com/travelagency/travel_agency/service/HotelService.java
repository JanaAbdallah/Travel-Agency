package com.travelagency.travel_agency.service;

import com.travelagency.travel_agency.model.Hotel;
import com.travelagency.travel_agency.model.Recommendation;
import com.travelagency.travel_agency.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private NotificationService notificationService;

    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public List<Hotel> searchHotelsByLocation(String location) {
        return hotelRepository.findByLocation(location);
    }

    public Hotel bookRoom(Long hotelId, Long userId, String roomType) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        // Check available rooms logic here (same as before)
        switch (roomType.toLowerCase()) {
            case "single":
                if (hotel.getSingleRooms() <= 0) {
                    throw new RuntimeException("No single rooms available");
                }
                hotel.setSingleRooms(hotel.getSingleRooms() - 1);
                break;
            case "double":
                if (hotel.getDoubleRooms() <= 0) {
                    throw new RuntimeException("No double rooms available");
                }
                hotel.setDoubleRooms(hotel.getDoubleRooms() - 1);
                break;
            case "family":
                if (hotel.getFamilyRooms() <= 0) {
                    throw new RuntimeException("No family rooms available");
                }
                hotel.setFamilyRooms(hotel.getFamilyRooms() - 1);
                break;
            default:
                throw new RuntimeException("Invalid room type");
        }

        hotelRepository.save(hotel);

        // Generate recommendations
        List<Recommendation> recommendations = recommendationService.generateRecommendations(userId, hotel.getLocation());

        // Send notification
        notificationService.sendNotification(userId, "Your room in " + hotel.getName() + " has been successfully booked.");

        return hotel;
    }

}
