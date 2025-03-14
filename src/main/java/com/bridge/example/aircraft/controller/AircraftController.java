package com.bridge.example.aircraft.controller;

import com.bridge.example.aircraft.entity.Aircraft;
import com.bridge.example.aircraft.service.AircraftService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {

    private final AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping("/")
    public List<Aircraft> getAllAircraft() {
        return this.aircraftService.getAll();
    }
    @GetMapping("/{id}/")
    public Aircraft getAircraftById(@PathVariable("id") long id) {
        return this.aircraftService.getById(id);
    }

    @PostMapping("/")
    public Aircraft createAircraft(@RequestBody Aircraft aircraft) {
        return this.aircraftService.saveAircraft(aircraft);
    }
}
