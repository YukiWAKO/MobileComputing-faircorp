package com.esme.spring.faircorp.web;

import com.esme.spring.faircorp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
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
        room.setStatus(room.getStatus() == Status.ON ? Status.OFF: Status.ON);
        room.getLights().forEach(light -> light.setStatus(room.getStatus()));
        if(room.getStatus() == Status.OFF){
            room.setLevel(0);
        }else{
            room.setLevel(50);
        }
//      room.getLights().forEach(light -> light.setStatus(light.getStatus() == Status.ON ? Status.OFF: Status.ON));
        return new RoomDto(room);
    }

    @PutMapping(path ="/{roomId}/changeLight/{lightLevel}")
    public RoomDto changeLevel(@PathVariable Long roomId, String lightLevel){
        Room room = roomDao.findById(roomId).orElseThrow(IllegalArgumentException::new);
        int level = Integer.parseInt(lightLevel);
        room.setLevel(level);
        if(room.getStatus() == Status.OFF){
            room.setStatus(Status.ON);
        }
        if(room.getLevel() == 0){
            room.setStatus((Status.OFF));
        }
        return new RoomDto(room);
    }

    @PostMapping
    public RoomDto create(@RequestBody RoomDto dto){
        Room room = null;
        if(dto.getId() != null){
            room = roomDao.findById((dto.getId())).orElse(null);
        }
        if(room == null){
            room = roomDao.save(new Room(dto.getId(), dto.getName(), dto.getLevel(), dto.getStatus()));
        }

        return new RoomDto(room);
    }

    @DeleteMapping(path = "/{roomId}")
    public void delete(@PathVariable Long roomId){
        roomDao.deleteById(roomId);
    }

}
