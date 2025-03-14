package com.bridge.example.aircraft.controller;

import com.bridge.example.aircraft.entity.Pilot;
import com.bridge.example.aircraft.service.AircraftService;
import com.bridge.example.aircraft.service.PilotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pilot")
public class PilotController {

    private final PilotService pilotService;

    public PilotController(PilotService pilotService) {
        this.pilotService = pilotService;
    }

    @GetMapping("/")
    public List<Pilot> getAll() {
        return this.pilotService.getAll();
    }

    @GetMapping("/{id}/")
    public Pilot getById(@PathVariable int id) {
        return this.pilotService.get(id);
    }

    @PostMapping("/")
    public Pilot create(@RequestBody Pilot pilot) {
        return this.pilotService.save(pilot);
    }
}
