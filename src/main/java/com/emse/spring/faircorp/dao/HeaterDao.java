package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HeaterDao extends JpaRepository<Heater, Long> {
    @Modifying
    @Query("delete from Heater h where h.room.id = :id")
    void deleteAllHeaters(@Param("id") Long id);

    @Query("select h from Heater h where h.room.name = :name")
    List<Heater> findHeatersByRoomName(String name);
}
