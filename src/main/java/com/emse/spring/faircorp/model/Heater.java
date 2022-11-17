package com.emse.spring.faircorp.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="HEATER")
public class Heater {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column
    private Long power;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private HeaterStatus heaterStatus;

    @ManyToOne
    private Room room;

    public Heater() {}

    public Heater(String name, HeaterStatus status, Room room) {
        this.name = name;
        this.heaterStatus = status;
        this.room = room;
        Set<Heater> heaters = room.getHeaters();
        heaters.add(this);
        room.setHeaters(heaters);
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

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public HeaterStatus getHeaterStatus() {
        return heaterStatus;
    }

    public void setHeaterStatus(HeaterStatus status) {
        this.heaterStatus = status;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

