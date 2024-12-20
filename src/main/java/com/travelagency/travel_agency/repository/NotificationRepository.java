package com.travelagency.travel_agency.repository;

import com.travelagency.travel_agency.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
