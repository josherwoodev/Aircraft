package com.bridge.example.aircraft.controller;

import com.bridge.example.aircraft.entity.Pilot;
import com.bridge.example.aircraft.service.PilotService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PilotController.class)
@AutoConfigureMockMvc
class PilotControllerTest {

    public static final String FIRST_NAME = "Chesty";
    public static final String LAST_NAME = "Puller";
    public static final int AGE = 420;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    PilotService pilotService;

    private Pilot pilot;

    @BeforeEach
    void setUp() {
        this.pilot = new Pilot(FIRST_NAME, LAST_NAME, AGE);
    }

    @Test
    void getAllPilots() throws Exception {
        when(this.pilotService.getAll()).thenReturn(List.of(this.pilot));

        MockitoAnnotations.openMocks(this);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/pilot/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$.*").isNotEmpty())
                .andExpect(jsonPath("$[0].id").value(Long.valueOf(0)));
    }

    @Test
    void getPilotById() throws Exception {
        when(this.pilotService.get(0)).thenReturn(this.pilot);

        MockitoAnnotations.openMocks(this);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/pilot/0/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*").isNotEmpty())
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(LAST_NAME))
                .andExpect(jsonPath("$.age").value(AGE));
    }

    @Test
    void createPilot() throws Exception {
        when(this.pilotService.save(any(Pilot.class))).thenReturn(this.pilot);

        MockitoAnnotations.openMocks(this);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/pilot/").contentType(MediaType.APPLICATION_JSON).content(this.objectMapper.writeValueAsString(this.pilot)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(Long.valueOf(0)))
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(LAST_NAME))
                .andExpect(jsonPath("$.age").value(AGE));
    }
}