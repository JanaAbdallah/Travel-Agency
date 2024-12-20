package com.travelagency.travel_agency.repository;

import com.travelagency.travel_agency.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByLocation(String location);
    List<Recommendation> findByUserId(Long userId);
}
