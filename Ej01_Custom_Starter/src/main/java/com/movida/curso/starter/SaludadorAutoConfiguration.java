package com.movida.curso.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.curso.util.Despedidor;
import com.curso.util.Saludador;

//
// src/main/resources/META-INF/spring.factories
//

@Configuration
@ConditionalOnClass(Saludador.class)
@EnableConfigurationProperties(SaludadorProperties.class)
public class SaludadorAutoConfiguration {

	@Autowired
	private SaludadorProperties saludadorProperties;

	//spring.main.allow-bean-definition-overriding
	@Bean
	@ConditionalOnMissingBean
	Saludador saludador() {
		String mensaje = saludadorProperties.getMensaje();
		if(mensaje==null) {
			mensaje = "Hola que tal, ";
		}
		return new Saludador(mensaje);
	}
	
	//spring.main.allow-bean-definition-overriding
	@Bean
	//@ConditionalOnMissingBean
	Despedidor despedidor() {
		String mensaje = saludadorProperties.getMensajeDespedida();
		if(mensaje==null) {
			mensaje = "Hasta luego Lucas, ";
		}
		return new Despedidor(mensaje);
	}	

}



