package com.esme.spring.faircorp.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RoomDaoImpl implements RoomDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Room> findOnRooms(){
        String jpql = "select lt from Room lt where lt.status = :value";
        return em.createQuery(jpql, Room.class)
                .setParameter("value", Status.ON)
                .getResultList();
    }
}
