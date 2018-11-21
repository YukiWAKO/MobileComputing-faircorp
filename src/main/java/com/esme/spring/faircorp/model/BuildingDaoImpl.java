package com.esme.spring.faircorp.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BuildingDaoImpl implements BuildingDaoCustom{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Building> findOnBuildings(){
        String jpql = "select lt from Building lt where lt.status = :value";
        return em.createQuery(jpql, Building.class)
                .setParameter("value", Status.ON)
                .getResultList();
    }
}
