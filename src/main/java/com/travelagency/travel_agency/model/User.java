package com.travelagency.travel_agency.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_booked_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> bookedEvents = new ArrayList<>();

    public void bookEvent(Event event) {
        bookedEvents.add(event);
    }

    public void cancelEvent(Event event) {
        bookedEvents.remove(event);
    }
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @ManyToMany
    @JoinTable(
            name = "user_hotel_bookings",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_id")
    )
    private List<Hotel> bookedHotels;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Hotel> getBookedHotels() {
        return bookedHotels;
    }

    public void setBookedHotels(List<Hotel> bookedHotels) {
        this.bookedHotels = bookedHotels;
    }
}
