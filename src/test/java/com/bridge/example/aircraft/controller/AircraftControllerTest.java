package com.bridge.example.aircraft.controller;

import com.bridge.example.aircraft.entity.Aircraft;
import com.bridge.example.aircraft.entity.Engine;
import com.bridge.example.aircraft.entity.EngineType;
import com.bridge.example.aircraft.service.AircraftService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AircraftController.class)
@AutoConfigureMockMvc
class AircraftControllerTest {

    public static final String AIRFRAME = "Dog House";
    public static final String PILOT = "Snoopy, the Beagle";
    public static final String SERIAL_NUMBER = "321C";
    public static final String MANUFACTURER = "Saab";
    public static final double VOL = 12.5;
    public static final String FUEL_TYPE = "Jet B";

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    AircraftService aircraftService;

    private Aircraft aircraft;

    @BeforeEach
    void setUp() {
        this.aircraft = new Aircraft(AIRFRAME, PILOT, new Engine(SERIAL_NUMBER, new EngineType(MANUFACTURER, VOL, FUEL_TYPE)));
    }

    @Test
    void getAllAircraft() throws Exception {
        when(this.aircraftService.getAll()).thenReturn(List.of(this.aircraft));

        MockitoAnnotations.openMocks(this);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/aircraft/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$.*").isNotEmpty())
                .andExpect(jsonPath("$[0].id").value(0));
    }

    @Test
    void getAircraft() throws Exception {
        when(this.aircraftService.getById(0)).thenReturn(this.aircraft);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/aircraft/0/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(0));
    }

    @Test
    void createAircraft() throws Exception {
        when(this.aircraftService.saveAircraft(Mockito.any(Aircraft.class))).thenReturn(this.aircraft);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/aircraft/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(new Aircraft(AIRFRAME, PILOT, new Engine(SERIAL_NUMBER, new EngineType(MANUFACTURER, VOL, FUEL_TYPE))))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.airframe").value(AIRFRAME))
                .andExpect(jsonPath("$.pilot").value(PILOT))
                .andExpect(jsonPath("$.engine.id").value(0))
                .andExpect(jsonPath("$.engine.serialNumber").value(SERIAL_NUMBER))
                .andExpect(jsonPath("$.engine.engineType.id").value(0))
                .andExpect(jsonPath("$.engine.engineType.manufacturer").value(MANUFACTURER))
                .andExpect(jsonPath("$.engine.engineType.volume").value(12.5))
                .andExpect(jsonPath("$.engine.engineType.fuelType").value(FUEL_TYPE));
    }
}