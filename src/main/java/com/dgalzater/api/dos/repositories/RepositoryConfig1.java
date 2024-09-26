package com.dgalzater.api.dos.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dgalzater.api.dos.models.City;

@Repository
public class RepositoryConfig1 {
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public RepositoryConfig1(@Qualifier("jdbcTemplateBdOne") JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	public List<City> findAllCities(){
		String sql = "SELECT * FROM city ORDER BY city_id ASC"; 
		
		return jdbc.query(sql, (rs,rowNum)->mapRowToCity(rs));
	}
	
	public City saveCity(City city) {
		String sql = "INSERT INTO city (city_id, city, country_id, last_update) VALUES (?, ?, ?, ?)";
		
		jdbc.update(sql,city.getId(),city.getCiudad(),city.getIdCiudad(),city.getLastUpdate());
		
		return city;
	}
	
	public City updateCity(City city) {
		String sql = "UPDATE city SET city_id=?, city=?, country_id=?, last_update=? WHERE city_id=?";
		
		jdbc.update(sql,city.getId(),city.getCiudad(),city.getIdCiudad(),city.getLastUpdate(),city.getId());
		
		return city;
	}
	
	public Boolean existCity(Long id) {
		String sql = "SELECT COUNT(*) FROM city WHERE city_id=?";
		
		Integer count = jdbc.queryForObject(sql, Integer.class, id);
		
		return count != null && count > 0;
	}
	
	public Integer deleteById(Long id) {
		String sql = "DELETE FROM city WHERE city_id=?";
		return jdbc.update(sql,id);
	}
	
	public City mapRowToCity(ResultSet rs) throws SQLException{
		City city = new City();
		city.setId(rs.getLong("city_id"));
		city.setCiudad(rs.getString("city"));
		city.setIdCiudad(rs.getInt("country_id"));
		city.setLastUpdate(rs.getTimestamp("last_update"));
		
		return city;
	}
	
	

}
