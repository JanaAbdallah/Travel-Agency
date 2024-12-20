package com.travelagency.travel_agency.controller;

import com.travelagency.travel_agency.model.Recommendation;
import com.travelagency.travel_agency.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateRecommendations(@RequestParam Long userId, @RequestParam String location) {
        recommendationService.generateRecommendations(userId, location);
        return ResponseEntity.ok("Recommendations generated for user " + userId);
    }

    @PostMapping("/add")
    public ResponseEntity<Recommendation> addRecommendation(@RequestBody Recommendation recommendation) {
        Recommendation addedRecommendation = recommendationService.addRecommendation(recommendation);
        return ResponseEntity.ok(addedRecommendation);
    }
}
