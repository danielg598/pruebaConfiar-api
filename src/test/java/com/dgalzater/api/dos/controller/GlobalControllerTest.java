package com.dgalzater.api.dos.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dgalzater.api.dos.controllers.GlobalController;
import com.dgalzater.api.dos.models.City;
import com.dgalzater.api.dos.services.Services;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GlobalControllerTest {
	
	private MockMvc mockMvc;

    @Mock
    private Services service;

    @InjectMocks
    private GlobalController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGetCities() throws Exception {
        when(service.getAllCities()).thenReturn(Arrays.asList(new City()));

        mockMvc.perform(get("/api/getCities"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.length()").value(1));

        verify(service, times(1)).getAllCities();
    }

    @Test
    void testAddCity() throws Exception {
        City city = new City();
        city.setId(1L);
        when(service.saveCity(any(City.class))).thenReturn(city);

        ObjectMapper objectMapper = new ObjectMapper();
        String cityJson = objectMapper.writeValueAsString(city);

        mockMvc.perform(post("/api/postCity")
               .contentType(MediaType.APPLICATION_JSON)
               .content(cityJson))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1));

        verify(service, times(1)).saveCity(any(City.class));
    }
    
    

    @Test
    void testDeleteCity() throws Exception {
        Long id = 1L;
        when(service.deleteCity(id)).thenReturn(true);

        mockMvc.perform(delete("/api/deleteCity/{id}", id))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.message").value("se elimino ciudad con el id  1"));

        verify(service, times(1)).deleteCity(id);
    }

}
