package com.dgalzater.api.dos.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dgalzater.api.dos.models.City;
import com.dgalzater.api.dos.repositories.RepositoryConfig1;
import com.dgalzater.api.dos.repositories.RepositoryConfig2;
import com.dgalzater.api.dos.services.Services;

public class ServicesTest {
	
	@Mock
    private RepositoryConfig1 repo1;

    @Mock
    private RepositoryConfig2 repo2;

    @InjectMocks
    private Services service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testGetAllCities() {
        // Simula el comportamiento del repositorio
        when(repo1.findAllCities()).thenReturn(Arrays.asList(new City()));

        List<City> cities = service.getAllCities();

        // Verifica que el servicio devuelva la lista simulada
        assertNotNull(cities);
        assertEquals(1, cities.size());
        verify(repo1, times(1)).findAllCities(); // Verifica que el método fue llamado exactamente una vez
    }

    @Test
    void testSaveCity() {
        City city = new City();
        when(repo1.saveCity(city)).thenReturn(city);

        City savedCity = service.saveCity(city);

        assertNotNull(savedCity);
        verify(repo1, times(1)).saveCity(city);
    }
    
    @Test
    void testUpdateCity() {
        // Datos de ejemplo
        City city = new City();
        city.setId(1L);
        city.setCiudad("New City");
        city.setIdCiudad(100);
        
        // Simulamos el comportamiento del repositorio
        when(repo1.updateCity(any(City.class))).thenReturn(city);

        // Llamamos al método de servicio
        City updatedCity = service.updateCity(city);

        // Verificamos que el repositorio fue llamado correctamente
        verify(repo1, times(1)).updateCity(city);

        // Afirmaciones para comprobar el resultado
        assertNotNull(updatedCity);
        assertEquals(1L, updatedCity.getId());
        assertEquals("New City", updatedCity.getCiudad());
        assertEquals(100, updatedCity.getIdCiudad());
    }

    @Test
    void testDeleteCity() {
        Long id = 1L;
        when(repo1.existCity(id)).thenReturn(true);
        when(repo1.deleteById(id)).thenReturn(1); // Simula la eliminación

        Boolean result = service.deleteCity(id);

        assertTrue(result);
        verify(repo1, times(1)).existCity(id);
        verify(repo1, times(1)).deleteById(id);
    }

}
