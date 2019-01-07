package com.esme.spring.faircorp.web;

import com.esme.spring.faircorp.model.Room;
import com.esme.spring.faircorp.model.Status;

public class RoomDto {

    private Long id;
    private String name;
    private Integer level;
    private Status status;

    public RoomDto(){}

    public RoomDto(Room room){
        this.id = room.getId();
        this.name = room.getName();
        this.level = room.getLevel();
        this.status = room.getStatus();
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public Integer getLevel() { return level; }

    public Status getStatus() { return status; }
}
