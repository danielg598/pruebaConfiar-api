//package com.dgalzater.api.dos.services;
//
//import static org.junit.jupiter.api.Assertions.*;
////import static org.mockito.Mockito.*;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
////import org.mockito.InjectMocks;
////import org.mockito.Mock;
////import org.mockito.MockitoAnnotations;
//
//import com.dgalzater.api.dos.models.City;
//import com.dgalzater.api.dos.models.Cliente;
//import com.dgalzater.api.dos.repositories.RepositoryConfig1;
//import com.dgalzater.api.dos.repositories.RepositoryConfig2;
//
//public class ServicesTest {
//
//    @Mock
//    private RepositoryConfig1 repo1;
//
//    @Mock
//    private RepositoryConfig2 repo2;
//
//    @InjectMocks
//    private Services service;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testGetAllCities() {
//        City city1 = new City();
//        city1.setId(1L);
//        city1.setCiudad("Madrid");
//
//        City city2 = new City();
//        city2.setId(2L);
//        city2.setCiudad("Barcelona");
//
//        List<City> cities = Arrays.asList(city1, city2);
//
//        when(repo1.findAllCities()).thenReturn(cities);
//
//        List<City> result = service.getAllCities();
//
//        assertEquals(2, result.size());
//        assertEquals("Madrid", result.get(0).getCiudad());
//    }
//
//    @Test
//    public void testSaveCity() {
//        City city = new City();
//        city.setId(1L);
//        city.setCiudad("Sevilla");
//
//        when(repo1.saveCity(any(City.class))).thenReturn(city);
//
//        City result = service.saveCity(city);
//
//        assertNotNull(result);
//        assertEquals("Sevilla", result.getCiudad());
//    }
//
//    @Test
//    public void testDeleteCity() {
//        Long id = 1L;
//
//        when(repo1.existCity(id)).thenReturn(true);
//        when(repo1.deleteById(id)).thenReturn(1);
//
//        Boolean result = service.deleteCity(id);
//
//        assertTrue(result);
//    }
//
//    @Test
//    public void testDeleteCityNotFound() {
//        Long id = 999L;
//
//        when(repo1.existCity(id)).thenReturn(false);
//
//        Boolean result = service.deleteCity(id);
//
//        assertFalse(result);
//    }
//
//    @Test
//    public void testGetAllClients() {
//        Cliente client1 = new Cliente();
//        client1.setId(1L);
//        client1.setCompany("Company1");
//
//        Cliente client2 = new Cliente();
//        client2.setId(2L);
//        client2.setCompany("Company2");
//
//        List<Cliente> clients = Arrays.asList(client1, client2);
//
//        when(repo2.findClient()).thenReturn(clients);
//
//        List<Cliente> result = service.getAllClients();
//
//        assertEquals(2, result.size());
//        assertEquals("Company1", result.get(0).getCompany());
//    }
//}
