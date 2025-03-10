package com.bridge.example.aircraft.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class AircraftControllerTest {

    public static final String AIRFRAME = "Dog House";
    public static final String PILOT = "Snoopy, the Beagle";
    public static final String FUEL_TYPE = "Jet-1.2";
    public static final String VOL = "12.0";
    public static final String MANUFACTURER = "Saab";

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllAircraft() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/aircraft"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$.*").isNotEmpty())
                .andExpect(jsonPath("$.[0].id").value(1));
    }

    @Test
    void getAircraft() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/aircraft/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void createAircraft() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/aircraft")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\"id\":1,\n" +
                                "\"airframe\":\"" + AIRFRAME + "\",\n" +
                                "\"pilot\":\"" + PILOT + "\",\n" +
                                "\"engine\":{\n" +
                                "\"id\":2,\n" +
                                "\"manufacturer\":\"" + MANUFACTURER + "\",\n" +
                                "\"volume\":\"" + VOL + "\",\n" +
                                "\"fuelType\":\"" + FUEL_TYPE + "\"\n" +
                                "}" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.airframe").value(AIRFRAME))
                .andExpect(jsonPath("$.pilot").value(PILOT))
                .andExpect(jsonPath("$.engine.id").value(2))
                .andExpect(jsonPath("$.engine.manufacturer").value(MANUFACTURER))
                .andExpect(jsonPath("$.engine.volume").value(12.0))
                .andExpect(jsonPath("$.engine.fuelType").value(FUEL_TYPE));
    }
}