package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;

import java.util.List;

public interface RoomDaoCustom {
    List<Room> findRoomByName(String name);
    Window findWindowById(Long room_id, Long window_id);
    Heater findHeaterById(Long room_id, Long heater_id);
}
