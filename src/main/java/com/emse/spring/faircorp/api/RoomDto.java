package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;

import java.util.HashSet;
import java.util.Set;

public class RoomDto {
    private Long id;
    private int floor;
    private String name;
    private Double currentTemperature;
    private Double targetTemperature;
    private Long buildingId;
    private String buildingName;
    private Set<WindowDto> windows = new HashSet<>();
    private Set<HeaterDto> heaters = new HashSet<>();

    public RoomDto() {}

    public RoomDto(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.floor = room.getFloor();
        this.currentTemperature = room.getCurrentTemperature();
        this.targetTemperature = room.getTargetTemperature();
        this.buildingId = room.getBuilding().getId();
        this.buildingName = room.getBuilding().getName();
        this.windows = windowsToDTO(room.getWindows());
        this.heaters = heatersToDTO(room.getHeaters());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Set<WindowDto> getWindows() {
        return windows;
    }

    public void setWindows(Set<WindowDto> windows) {
        this.windows = windows;
    }

    public Set<HeaterDto> getHeaters() {
        return heaters;
    }

    public void setHeaters(Set<HeaterDto> heaters) {
        this.heaters = heaters;
    }

    public Set<WindowDto> windowsToDTO(Set<Window> windows) {
        Set<WindowDto> result = new HashSet<>();
        if (windows != null) {
            for (Window window : windows) {
                result.add(new WindowDto(window));
            }
        }
        return result;
    }

    public Set<HeaterDto> heatersToDTO(Set<Heater> heaters) {
        Set<HeaterDto> result = new HashSet<>();
        if (windows != null) {
            for (Heater heater : heaters) {
                result.add(new HeaterDto(heater));
            }
        }
        return result;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}
