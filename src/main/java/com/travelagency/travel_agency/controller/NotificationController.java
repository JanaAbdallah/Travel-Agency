package com.travelagency.travel_agency.controller;

import com.travelagency.travel_agency.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public String sendNotification(@RequestParam Long userId, @RequestParam String message) {
        notificationService.sendNotification(userId, message);
        return "Notification sent to user: " + userId;
    }
}
