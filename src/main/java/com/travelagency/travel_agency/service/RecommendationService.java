package com.travelagency.travel_agency.service;

import com.travelagency.travel_agency.model.Recommendation;
import com.travelagency.travel_agency.repository.RecommendationRepository;
import com.travelagency.travel_agency.repository.HotelRepository;
import com.travelagency.travel_agency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Recommendation> getRecommendationsByLocation(String location) {
        return recommendationRepository.findByLocation(location);
    }

    public List<Recommendation> generateRecommendations(Long userId, String location) {
        List<Recommendation> recommendations = recommendationRepository.findByLocation(location);

        // Attach user dynamically to the recommendations
        recommendations.forEach(recommendation -> recommendation.setUser(userRepository.findById(userId).orElse(null)));
        return recommendations;
    }
    public Recommendation addRecommendation(Recommendation recommendation) {
        // Validate hotel exists if provided
        if (recommendation.getHotel() != null && !hotelRepository.existsById(recommendation.getHotel().getId())) {
            throw new RuntimeException("Invalid hotel ID");
        }

        // Validate user exists if provided
        if (recommendation.getUser() != null && !userRepository.existsById(recommendation.getUser().getId())) {
            throw new RuntimeException("Invalid user ID");
        }

        return recommendationRepository.save(recommendation);
    }



}
