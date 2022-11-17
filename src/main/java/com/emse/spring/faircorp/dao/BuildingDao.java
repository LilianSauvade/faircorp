package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.api.BuildingDto;
import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface BuildingDao extends JpaRepository<Building, Long> {
    @Query("select b from Building b where b.name = :criteria or b.address = :criteria")
    BuildingDto findByNameOrAddress(@Param("criteria") String criteria);

    @Query("select r from Room r where r.building.id = :id")
    Set<Room> getAllRooms(@Param("id") Long id);
}
