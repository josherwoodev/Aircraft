package com.bridge.example.aircraft.service;

import com.bridge.example.aircraft.entity.Aircraft;
import com.bridge.example.aircraft.repository.AircraftRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftService {

    private final AircraftRepository aircraftRepository;

    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    public Aircraft saveAircraft(Aircraft aircraft) {
        return this.aircraftRepository.save(aircraft);
    }

    public List<Aircraft> getAll() {
        return this.aircraftRepository.findAll();
    }

    public Aircraft getById(long id) {
        return this.aircraftRepository.findById(id).orElse(null);
    }
}
