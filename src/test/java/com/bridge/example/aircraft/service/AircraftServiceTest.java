package com.bridge.example.aircraft.service;

import com.bridge.example.aircraft.entity.Aircraft;
import com.bridge.example.aircraft.repository.AircraftRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AircraftServiceTest {

    @Mock
    private AircraftRepository aircraftRepository;
    private Aircraft aircraft;

    @InjectMocks
    private AircraftService aircraftService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.aircraft = new Aircraft(null, null, null);
    }

    @Test
    void saveAircraft() {
        when(this.aircraftRepository.save(this.aircraft)).thenReturn(this.aircraft);

        var actualAircraft = this.aircraftService.saveAircraft(this.aircraft);

        assertNotNull(actualAircraft);
        assertEquals(this.aircraft.getId(), actualAircraft.getId());
        verify(this.aircraftRepository, times(1)).save(any(Aircraft.class));
    }

    @Test
    void getAll() {
        when(this.aircraftRepository.findAll()).thenReturn(Arrays.asList(this.aircraft));

        var actualList = this.aircraftService.getAll();

        assertNotNull(actualList);
        assertEquals(1, actualList.size());
        assertEquals(this.aircraft.getId(), actualList.get(0).getId());
        verify(this.aircraftRepository, times(1)).findAll();
    }

    @Test
    void getById() {
        Optional<Aircraft> aircraft1 = Optional.of(this.aircraft);
        when(this.aircraftRepository.findById(1L)).thenReturn(aircraft1);

        var actualAircraft = this.aircraftService.getById(1L);

        assertNotNull(actualAircraft);
        assertEquals(this.aircraft.getId(), actualAircraft.getId());
    }
}