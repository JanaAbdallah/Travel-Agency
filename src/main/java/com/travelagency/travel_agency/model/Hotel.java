package com.travelagency.travel_agency.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    private Integer singleRooms; // Number of single rooms available
    private Integer doubleRooms; // Number of double rooms available
    private Integer familyRooms; // Number of family rooms available

    // Getters and setters (if not using Lombok)
    public Integer getAvailableRooms() {
        return (singleRooms != null ? singleRooms : 0) +
                (doubleRooms != null ? doubleRooms : 0) +
                (familyRooms != null ? familyRooms : 0);
    }
    public void setAvailableRooms(Integer availableRooms) {
        this.singleRooms = availableRooms;
    }

    public Hotel(String name, String location, Integer singleRooms, Integer doubleRooms, Integer familyRooms) {
        this.name = name;
        this.location = location;
        this.singleRooms = singleRooms;
        this.doubleRooms = doubleRooms;
        this.familyRooms = familyRooms;

    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getLocation() {
        return location;

    }
    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getSingleRooms() {
        return singleRooms;
    }
    public void setSingleRooms(Integer singleRooms) {
        this.singleRooms = singleRooms;
    }
    public Integer getDoubleRooms() {
        return doubleRooms;
    }
    public void setDoubleRooms(Integer doubleRooms) {
        this.doubleRooms = doubleRooms;
    }
    public Integer getFamilyRooms() {
        return familyRooms;
    }
    public void setFamilyRooms(Integer familyRooms) {
        this.familyRooms = familyRooms;
    }

}
