package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Room;

import java.util.HashSet;
import java.util.Set;

public class BuildingDto {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String zipCode;
    private Set<RoomDto> rooms;

    public BuildingDto() {}

    public BuildingDto(Building building) {
        this.id = building.getId();
        this.name = building.getName();
        this.address = building.getAddress();
        this.city = building.getCity();
        this.zipCode = building.getZipCode();
        this.rooms = roomsToDTO(building.getRooms());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Set<RoomDto> getRooms() {
        return rooms;
    }

    public void setRooms(Set<RoomDto> rooms) {
        this.rooms = rooms;
    }

    public Set<RoomDto> roomsToDTO(Set<Room> rooms) {
        Set<RoomDto> result = new HashSet<>();
        if (rooms != null) {
            for (Room room : rooms) {
                result.add(new RoomDto(room));
            }
        }
        return result;
    }
}
