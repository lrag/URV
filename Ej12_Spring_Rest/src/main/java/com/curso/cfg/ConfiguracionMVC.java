package com.curso.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@ComponentScan(basePackages = "com.curso.rest" ) //Y sub paquetes
@EnableWebMvc
public class ConfiguracionMVC implements WebMvcConfigurer {

	public ConfiguracionMVC() {
		super();
		System.out.println("Creando una instancia de ConfiguracionMVC");
	}
	
}

