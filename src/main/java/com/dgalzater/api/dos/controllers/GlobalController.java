package com.dgalzater.api.dos.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgalzater.api.dos.models.City;
import com.dgalzater.api.dos.models.Cliente;
import com.dgalzater.api.dos.services.Services;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api")
public class GlobalController {
	
	private Services servicio;

	public GlobalController(Services servicio) {
		this.servicio = servicio;
	}
	
	@GetMapping("/getCities")
	public ResponseEntity<List<City>> getCities(){
		List<City> city = servicio.getAllCities();
		return ResponseEntity.ok(city);
	}
	
	@GetMapping("/getClients")
	public ResponseEntity<List<Cliente>> getClients(){
		List<Cliente> clientes = servicio.getAllClients();
		return ResponseEntity.ok(clientes);
	}
	
	@PostMapping("/postCity")
	public ResponseEntity<City> addCity(@RequestBody City city) {
		City add = servicio.saveCity(city);
		return ResponseEntity.ok(add);
	}
	
	@PostMapping("/postClients")
	public ResponseEntity<Cliente> postMethodName(@RequestBody Cliente cliente) {
		Cliente add = servicio.saveClient(cliente);
		return ResponseEntity.ok(add);
	}
	
	
	@PutMapping("/updateCity/{id}")
	public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City city) {
		city.setId(id);
		City update = servicio.updateCity(city);
		return ResponseEntity.ok(update);
	}
	
	@PutMapping("updateClient/{id}")
	public ResponseEntity<Cliente> updateClient(@PathVariable Long id, @RequestBody Cliente cliente) {
		cliente.setId(id);
		Cliente update = servicio.updateClient(cliente);
		return ResponseEntity.ok(update);
	}
	
	@DeleteMapping("/deleteCity/{id}")
	public ResponseEntity<Map<String, String>> deleteCity(@PathVariable Long id){
		Boolean res = servicio.deleteCity(id);
		
		if(res) {
			return ResponseEntity.ok(Map.of("message","se elimino ciudad con el id "+" "+id));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","el id"+" "+id+" no existe"));
		}
	}
	
	@DeleteMapping("/deleteClient/{id}")
	public ResponseEntity<Map<String, String>> deleteClient(@PathVariable long id){
		Boolean res = servicio.deleteClient(id);
		
		if(res) {
			return ResponseEntity.ok(Map.of("message","se elimino cliente con id "+" "+id));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","el id que se ingreso no existe"));
		}
	}
	
	
	
	

}
