package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.api.BuildingDto;
import com.emse.spring.faircorp.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BuildingDaoTest {
    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private HeaterDao heaterDao;

    @Test
    public void shouldFindByNameOrAddress() {
        BuildingDto buildingDto = buildingDao.findByNameOrAddress("Maison des Eleves");
        Assertions.assertThat(buildingDto.getName()).isEqualTo("Maison des Eleves");
    }

    @Test
    public void shouldGetAllRooms() {
        Set<Room> rooms = buildingDao.getAllRooms(-10L);
        Assertions.assertThat(rooms.size()).isEqualTo(2);
    }
}