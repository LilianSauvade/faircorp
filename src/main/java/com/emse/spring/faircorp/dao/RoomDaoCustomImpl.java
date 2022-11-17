package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RoomDaoCustomImpl implements RoomDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Room> findRoomByName(String name) {
        String jpql = "select r from Room r where r.name = :name";
        return em.createQuery(jpql,Room.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public Window findWindowById(Long room_id, Long window_id) {
        String jpql = "select w from Window w where w.id =:window_id and w.room.id = :room_id";
        return em.createQuery(jpql, Window.class)
                .setParameter("room_id", room_id)
                .setParameter("window_id", window_id)
                .getSingleResult();
    }

    @Override
    public Heater findHeaterById(Long room_id, Long heater_id) {
        String jpql = "select h from Heater h where h.id = :heater_id and h.room.id = :room_id";
        return em.createQuery(jpql, Heater.class)
                .setParameter("room_id", room_id)
                .setParameter("heater_id", heater_id)
                .getSingleResult();
    }
}