package com.travelagency.travel_agency.service;

import com.travelagency.travel_agency.model.Notification;
import com.travelagency.travel_agency.model.User;
import com.travelagency.travel_agency.repository.NotificationRepository;
import com.travelagency.travel_agency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public void sendNotification(Long userId, String message) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setStatus("PENDING");
        notification.setTimestamp(LocalDateTime.now());

        notificationRepository.save(notification);
    }
}
