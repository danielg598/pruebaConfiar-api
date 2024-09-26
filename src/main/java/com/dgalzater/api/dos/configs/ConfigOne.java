package com.dgalzater.api.dos.configs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class ConfigOne {

    @Bean(name = "dsPrimeraBd")
    @ConfigurationProperties(prefix = "config.one")
    DataSource ds() {
		DriverManagerDataSource dm = new DriverManagerDataSource();
		dm.setDriverClassName("org.postgresql.Driver");
		dm.setUrl("jdbc:postgresql://localhost:5432/prueba");
		dm.setUsername("postgres");
		dm.setPassword("1234");
		return dm;
	}
	
	@Bean(name="jdbcTemplateBdOne")
	JdbcTemplate jdbc(@Qualifier("dsPrimeraBd") DataSource ds) {
		return new JdbcTemplate(ds);
	}

}
