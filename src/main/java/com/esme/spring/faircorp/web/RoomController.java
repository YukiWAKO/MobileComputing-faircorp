package com.esme.spring.faircorp.web;

import com.esme.spring.faircorp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {

    @Autowired
    private RoomDao roomDao;
    @Autowired
    private LightDao lightDao;

    @GetMapping
    public List<RoomDto> finAll(){
        return roomDao.findAll()
                .stream()
                .map(RoomDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{roomId}")
    public RoomDto findById(@PathVariable Long roomId){
        return roomDao.findById(roomId).map(room -> new RoomDto(room)).orElse(null);
    }

    @PutMapping(path ="/{roomId}/switch")
    public RoomDto switchStatus(@PathVariable Long roomId){
        Room room = roomDao.findById(roomId).orElseThrow(IllegalArgumentException::new);
        room.getLights().forEach(light -> light.setStatus(light.getStatus() == Status.ON ? Status.OFF: Status.ON));
        return new RoomDto(room);
    }

    @PostMapping
    public RoomDto create(@RequestBody RoomDto dto){
        Room room = null;
        if(dto.getId() != null){
            room = roomDao.findById((dto.getId())).orElse(null);
        }
        if(room == null){
            room = roomDao.save(new Room(dto.getId(), dto.getName(), dto.getLevel()));
        }

        return new RoomDto(room);
    }

    @DeleteMapping(path = "/{roomId}")
    public void delete(@PathVariable Long roomId){
        roomDao.deleteById(roomId);
    }

}
