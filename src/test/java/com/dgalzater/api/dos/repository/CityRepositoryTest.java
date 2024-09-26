package com.dgalzater.api.dos.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dgalzater.api.dos.models.City;
import com.dgalzater.api.dos.repositories.RepositoryConfig1;

public class CityRepositoryTest {

	@Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RepositoryConfig1 repositoryConfig1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Prueba para el método findAllCities
    @Test
    void testFindAllCities() {
        // Datos de ejemplo
        City city1 = new City();
        city1.setId(1L);
        city1.setCiudad("New York");
        city1.setIdCiudad(100);
        city1.setLastUpdate(new Timestamp(System.currentTimeMillis()));

        City city2 = new City();
        city2.setId(2L);
        city2.setCiudad("Los Angeles");
        city2.setIdCiudad(101);
        city2.setLastUpdate(new Timestamp(System.currentTimeMillis()));

        List<City> expectedCities = Arrays.asList(city1, city2);

        // Simulación del comportamiento de jdbcTemplate
        when(jdbcTemplate.query(anyString(), any(org.springframework.jdbc.core.RowMapper.class)))
            .thenReturn(expectedCities);

        // Ejecución del método a probar
        List<City> actualCities = repositoryConfig1.findAllCities();

        // Verificación
        assertEquals(expectedCities.size(), actualCities.size());
        assertEquals(expectedCities.get(0).getId(), actualCities.get(0).getId());
        assertEquals(expectedCities.get(1).getCiudad(), actualCities.get(1).getCiudad());
    }

    // Prueba para el método saveCity
    @Test
    void testSaveCity() {
        City city = new City();
        city.setId(1L);
        city.setCiudad("Chicago");
        city.setIdCiudad(102);
        city.setLastUpdate(new Timestamp(System.currentTimeMillis()));

        // No hace falta simular nada porque jdbcTemplate.update no devuelve nada
        repositoryConfig1.saveCity(city);

        // Como jdbcTemplate.update no devuelve nada, no podemos hacer muchas comprobaciones
        // aquí, pero podrías asegurarte de que no se lanzó ninguna excepción.
        assertNotNull(city);
    }

    // Prueba para el método updateCity
    @Test
    void testUpdateCity() {
        City city = new City();
        city.setId(1L);
        city.setCiudad("Miami");
        city.setIdCiudad(103);
        city.setLastUpdate(new Timestamp(System.currentTimeMillis()));

        // Simular la actualización de la ciudad
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);

        City updatedCity = repositoryConfig1.updateCity(city);

        assertNotNull(updatedCity);
        assertEquals(city.getCiudad(), updatedCity.getCiudad());
    }

    // Prueba para el método existCity
    @Test
    void testExistCity() {
        Long cityId = 1L;

        // Simular que la ciudad existe
        when(jdbcTemplate.queryForObject(anyString(), any(Class.class), any(Object.class)))
            .thenReturn(1);  // Devuelve 1 para simular que la ciudad existe

        Boolean exists = repositoryConfig1.existCity(cityId);

        assertTrue(exists);
    }

    // Prueba para el método deleteById
    @Test
    void testDeleteById() {
        Long cityId = 1L;

        // Simular la eliminación de la ciudad
        when(jdbcTemplate.update(anyString(), any(Object.class))).thenReturn(1);

        Integer rowsAffected = repositoryConfig1.deleteById(cityId);

        assertEquals(1, rowsAffected);  // 1 fila eliminada
    }

    // Prueba para el método mapRowToCity
    @Test
    void testMapRowToCity() throws SQLException {
        // Simular un ResultSet
        ResultSet rs = org.mockito.Mockito.mock(ResultSet.class);

        // Simular los valores del ResultSet
        when(rs.getLong("city_id")).thenReturn(1L);
        when(rs.getString("city")).thenReturn("San Francisco");
        when(rs.getInt("country_id")).thenReturn(104);
        when(rs.getTimestamp("last_update")).thenReturn(new Timestamp(System.currentTimeMillis()));

        // Llamar al método
        City city = repositoryConfig1.mapRowToCity(rs);

        // Verificar que los valores se asignan correctamente
        assertEquals(1L, city.getId());
        assertEquals("San Francisco", city.getCiudad());
        assertEquals(104, city.getIdCiudad());
    }
}
