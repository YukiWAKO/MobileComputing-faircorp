package com.esme.spring.faircorp.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Building {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "building")
    public List<Room> rooms;

    public Building(){}

    public Building(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
