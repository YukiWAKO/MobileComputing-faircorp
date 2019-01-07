package com.esme.spring.faircorp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer level;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "room")
    public List<Light> lights;
    @ManyToOne
    private Building building;

    public Room(){ }

    public Room(Long id, String name, Integer level, Status status){
        this.id = id;
        this.name = name;
        this.level = level;
        this.status = status;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<Light> getLights() {
        return lights;
    }

    public void setLights(List lights) {
        this.lights = lights;
    }

    public Status getStatus(){return status;}
    public void setStatus(Status status){this.status = status; }
}
