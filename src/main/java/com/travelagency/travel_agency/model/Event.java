package com.travelagency.travel_agency.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String date;

    private int availableTickets; // Add this field

    public void decrementTickets() {
        if (availableTickets > 0) {
            availableTickets--;
        } else {
            throw new RuntimeException("No tickets available");
        }
    }

    public void incrementTickets() {
        availableTickets++;
    }
    public int getAvailableTickets() {
        return availableTickets;
    }
    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }


    public Event(String name, String location, String date, int availableTickets) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.availableTickets = availableTickets;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

}
