package com.esme.spring.faircorp.web;

import com.esme.spring.faircorp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/buildings")
@Transactional
public class BuildingController {

    @Autowired
    private LightDao lightDao;
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private BuildingDao buildingDao;

    @GetMapping
    public List<BuildingDto> findAll(){
        return buildingDao.findAll()
                .stream()
                .map(BuildingDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public BuildingDto create(@RequestBody BuildingDto dto){
        Building building = null;
        if(dto.getId() != null){
            building = buildingDao.findById(dto.getId()).orElse(null);
        }
        if(building == null){
            building = buildingDao.save(new Building(dto.getId(),dto.getName()));
        }

        return new BuildingDto((building));
    }

    @GetMapping(path = "/{building_id}")
    public BuildingDto findById(@PathVariable Long building_id){
        return buildingDao.findById(building_id).map(building -> new BuildingDto(building)).orElse(null);
    }

    @DeleteMapping(path = "/{building_id}")
    public void delete(@PathVariable Long building_id){ buildingDao.deleteById(building_id); }
}
