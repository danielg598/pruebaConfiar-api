package com.dgalzater.api.dos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dgalzater.api.dos.models.City;
import com.dgalzater.api.dos.models.Cliente;
import com.dgalzater.api.dos.repositories.RepositoryConfig1;
import com.dgalzater.api.dos.repositories.RepositoryConfig2;

@Service
public class Services {

	private RepositoryConfig1 repo;
	private RepositoryConfig2 repo2;

	public Services(RepositoryConfig1 repo, RepositoryConfig2 repo2) {
		this.repo = repo;
		this.repo2 = repo2;
	}
	
	public List<City> getAllCities(){
		return repo.findAllCities();
	}
	
	public List<Cliente> getAllClients(){
		return repo2.findClient();
	}
	
	public City saveCity(City city) {
		return repo.saveCity(city);
	}
	
	public Cliente saveClient(Cliente cliente) {
		return repo2.saveClient(cliente);
	}
	
	public City updateCity(City city) {
		return repo.updateCity(city);
	}
	
	public Cliente updateClient(Cliente cliente) {
		return repo2.updateClient(cliente);
	}
	
	public Boolean deleteCity(Long id) {
		if(repo.existCity(id)) {
			int rowsAfected = repo.deleteById(id);
			return rowsAfected>0;
		}
		return false;
	}
	
	public Boolean deleteClient(Long id) {
		if(repo2.existId(id)) {
			Integer rowsAfected = repo2.deleteClient(id);
			return rowsAfected>0;
		}
		return false;
	}
	
}
