package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Room;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/buildings")
@Transactional
public class BuildingController {
    private final BuildingDao buildingDao;
    private final RoomDao roomDao;
    private final WindowDao windowDao;
    private final HeaterDao heaterDao;

    public BuildingController(BuildingDao buildingDao, RoomDao roomDao, WindowDao windowDao, HeaterDao heaterDao) {
        this.buildingDao = buildingDao;
        this.roomDao = roomDao;
        this.windowDao = windowDao;
        this.heaterDao = heaterDao;
    }

    @GetMapping
    public List<BuildingDto> findAll() {
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public BuildingDto findById(@PathVariable Long id) {
        return buildingDao.findById(id).map(BuildingDto::new).orElse(null);
    }

    @PostMapping
    public BuildingDto create(@RequestBody BuildingDto dto) {
        Building building = null;
        if (dto.getId() == null) {
            building = buildingDao.save(new Building(dto.getName(), dto.getAddress(), dto.getCity(), dto.getZipCode()));
        } else {
            building = buildingDao.getReferenceById(dto.getId());
            building.setName(dto.getName());
            building.setAddress(dto.getAddress());
            building.setCity(dto.getCity());
            building.setZipCode(dto.getZipCode());
        }
        building.setRooms(buildingDao.getAllRooms(dto.getId()));
        return new BuildingDto(building);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        Set<Room> rooms = buildingDao.getAllRooms(id);
        for (Room room : rooms) {
            windowDao.deleteAllWindows(room.getId());
            heaterDao.deleteAllHeaters(room.getId());
            roomDao.deleteById(room.getId());
        }
        buildingDao.deleteById(id);
    }

   @GetMapping(path = "/search")
   public BuildingDto findByNameOrAddress(String criteria) {
        return buildingDao.findByNameOrAddress(criteria);
   }

}
