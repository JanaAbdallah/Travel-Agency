package com.travelagency.travel_agency.repository;

import com.travelagency.travel_agency.controller.LoginRequest;
import com.travelagency.travel_agency.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

}

