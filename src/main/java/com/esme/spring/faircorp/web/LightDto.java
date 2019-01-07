package com.esme.spring.faircorp.web;

import com.esme.spring.faircorp.model.Light;
import com.esme.spring.faircorp.model.Room;
import com.esme.spring.faircorp.model.Status;

public class LightDto {

    private Long id;
    private Integer level;
    private Status status;
    private Long roomId;

    public LightDto() {
    }

    public LightDto(Light light) {
        this.id = light.getId();
        this.level = light.getRoom().getLevel();
        this.status = light.getRoom().getStatus();
        this.roomId = light.getRoom().getId();
    }

    public Long getId() {
        return id;
    }

    public Integer getLevel() {
        return level;
    }

    public Status getStatus() {
        return status;
    }
    public Long getRoomId(){
        return roomId;
    }
}
