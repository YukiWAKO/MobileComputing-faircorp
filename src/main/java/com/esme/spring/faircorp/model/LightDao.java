package com.esme.spring.faircorp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface LightDao extends JpaRepository<Light, Long>,LightDaoCustom {

}
