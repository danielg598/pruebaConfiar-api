package com.dgalzater.api.dos.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dgalzater.api.dos.models.Cliente;

@Repository
public class RepositoryConfig2 {
	
	private JdbcTemplate jdbc;

	@Autowired
	public RepositoryConfig2(@Qualifier("jdbcTemplateBdTwo") JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	public List<Cliente> findClient(){
		String sql = "SELECT * FROM clientes ORDER BY clienteid ASC";
		
		return jdbc.query(sql, (rs,rowNum)-> mapRowClient(rs));
	}
	
	public Cliente saveClient(Cliente cliente) {
		String sql = "INSERT INTO public.clientes(clienteid, cedula_ruc, nombrecia, nombrecontacto, direccioncli, fax, email, celular, fijo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbc.update(sql,cliente.getId(),cliente.getCedula(),cliente.getCompany(),cliente.getContacto(),cliente.getDireccion(),cliente.getFax(),cliente.getEmail(),cliente.getCelular(),cliente.getFijo());
		return cliente;
	}
	public Cliente updateClient(Cliente cliente) {
		String sql = "UPDATE public.clientes SET clienteid=?, cedula_ruc=?, nombrecia=?, nombrecontacto=?, direccioncli=?, fax=?, email=?, celular=?, fijo=? WHERE clienteid=?";
		jdbc.update(sql,cliente.getId(),cliente.getCedula(),cliente.getCompany(),cliente.getContacto(),cliente.getDireccion(),cliente.getFax(),cliente.getEmail(),cliente.getCelular(),cliente.getFijo(),cliente.getId());
		return cliente;
	}
	
	public Boolean existId(Long id) {
		String sql = "SELECT COUNT(*) FROM clientes WHERE clienteid=?";
		Integer count = jdbc.queryForObject(sql, Integer.class, id);
		return count != null && count>0;
	}
	
	public Integer deleteClient( Long id) {
		String sql = "DELETE FROM public.clientes WHERE clienteid=?";
		return jdbc.update(sql,id);
	}
	
	private Cliente mapRowClient(ResultSet rs) throws SQLException{
		Cliente cl = new Cliente();
		cl.setId(rs.getLong("clienteid"));
		cl.setCedula(rs.getString("cedula_ruc"));
		cl.setCompany(rs.getString("nombrecia"));
		cl.setContacto(rs.getString("nombrecontacto"));
		cl.setDireccion(rs.getString("direccioncli"));
		cl.setFax(rs.getString("fax"));
		cl.setEmail(rs.getString("email"));
		cl.setCelular(rs.getString("celular"));
		cl.setFijo(rs.getString("fijo"));
		return cl;
	}
	
	

}
