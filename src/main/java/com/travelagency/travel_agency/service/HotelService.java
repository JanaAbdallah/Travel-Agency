package com.travelagency.travel_agency.service;

import com.travelagency.travel_agency.model.Hotel;
import com.travelagency.travel_agency.model.Recommendation;
import com.travelagency.travel_agency.model.User;
import com.travelagency.travel_agency.repository.HotelRepository;
import com.travelagency.travel_agency.repository.UserRepository;
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
    private UserRepository userRepository;
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

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.getBookedHotels().add(hotel);
        userRepository.save(user);
        return hotel;
    }


    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public String cancelRoomBooking(Long hotelId, Long userId, String roomType) {
        // Find the hotel
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        // Find the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the user has booked this hotel
        if (!user.getBookedHotels().contains(hotel)) {
            throw new RuntimeException("User has not booked this hotel");
        }

        // Increment the room count based on the room type
        switch (roomType.toLowerCase()) {
            case "single":
                hotel.setSingleRooms(hotel.getSingleRooms() + 1);
                break;
            case "double":
                hotel.setDoubleRooms(hotel.getDoubleRooms() + 1);
                break;
            case "family":
                hotel.setFamilyRooms(hotel.getFamilyRooms() + 1);
                break;
            default:
                throw new RuntimeException("Invalid room type");
        }

        // Remove the hotel from the user's booked list
        user.getBookedHotels().remove(hotel);

        // Save updates to the database
        userRepository.save(user);
        hotelRepository.save(hotel);

        return "User " + userId + " has successfully canceled the booking for hotel: " + hotel.getName();
    }

}
