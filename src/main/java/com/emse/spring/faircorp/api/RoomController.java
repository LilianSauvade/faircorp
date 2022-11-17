package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.model.*;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {
    private final BuildingDao buildingDao;
    private final WindowDao windowDao;
    private final HeaterDao heaterDao;
    private final RoomDao roomDao;

    public RoomController(BuildingDao buildingDao, WindowDao windowDao, HeaterDao heaterDao, RoomDao roomDao) {
        this.buildingDao = buildingDao;
        this.windowDao = windowDao;
        this.heaterDao = heaterDao;
        this.roomDao = roomDao;
    }

    @GetMapping
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }


    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomDto::new).orElse(null);
    }

    @PutMapping(path = "/{room_id}/switchWindows")
    public ArrayList<WindowDto> switchWindows(@PathVariable(name="room_id") Long room_id) {
        Room room = roomDao.findById(room_id).orElseThrow(IllegalArgumentException::new);
        List<Window> windows = windowDao.findWindowsByRoomName(room.getName());
        ArrayList<WindowDto> list = new ArrayList<>();
        for (Window window : windows) {
            window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
            list.add(new WindowDto(window));
        }
        return list;
    }

    @PutMapping(path = "/{room_id}/switchHeaters")
    public ArrayList<HeaterDto> switchHeaters(@PathVariable("room_id") Long room_id) {
        Room room = roomDao.findById(room_id).orElseThrow(IllegalArgumentException::new);
        List<Heater> heaters = heaterDao.findHeatersByRoomName(room.getName());
        System.out.println("size : " + heaters.size());
        System.out.println("Name : " + room.getName() + " ; " + room.getId());
        ArrayList<HeaterDto> list = new ArrayList<>();
        for (Heater heater : heaters) {
            System.out.println("ID : " + heater.getId());
            heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON);
            list.add(new HeaterDto(heater));
        }
        return list;
    }

    @PostMapping
    public RoomDto create(@RequestBody RoomDto dto) {
        Building building = buildingDao.getReferenceById(dto.getBuildingId());
        Room room = null;
        // On creation id is not defined
        if (dto.getId() == null) {
            room = roomDao.save(new Room(dto.getName(), dto.getFloor(), building));
        }
        else {
            room = roomDao.getReferenceById(dto.getId());
            room.setName(dto.getName());
            room.setFloor(dto.getFloor());

        }
        room.setCurrentTemperature(dto.getCurrentTemperature());
        room.setTargetTemperature(dto.getTargetTemperature());
        return new RoomDto(room);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        windowDao.deleteAllWindows(id);
        heaterDao.deleteAllHeaters(id);
        roomDao.deleteById(id);
    }
}
