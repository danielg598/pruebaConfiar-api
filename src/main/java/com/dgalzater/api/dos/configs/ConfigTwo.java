package com.dgalzater.api.dos.configs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class ConfigTwo {
	
	@Bean(name="dsSegundaBd")
	@ConfigurationProperties(prefix="config.two")
	DataSource ds() {
		DriverManagerDataSource dm = new DriverManagerDataSource();
		dm.setDriverClassName("org.postgresql.Driver");
		dm.setUrl("jdbc:postgresql://localhost:5432/PEDIDOS");
		dm.setUsername("postgres");
		dm.setPassword("1234");
		return dm;
	}
	
	@Bean(name="jdbcTemplateBdTwo")
	JdbcTemplate jdbc(@Qualifier("dsSegundaBd") DataSource ds) {
		return new JdbcTemplate(ds);
	}

}
