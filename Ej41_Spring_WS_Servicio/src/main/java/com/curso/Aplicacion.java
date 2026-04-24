package com.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//
//http://localhost:8080/ws/clientes.wsdl
//


//PASOS A SEGUIR:
//
//1- Generar el xsd con 
//		la definición de tipos 
//		los parámetros recibidos en cada operación
//		las respuestas de cada operación
//
//2- Generar las clases a partir de las definiciones del XSD
//		Lo mejor es que lo haga el plugin de maven
//
//3- Crear el endpoint con las anotaciones de Spring-WS 
//		com.curso.endPoint.ClienteEndPoint
//
//4- Completar la configuracion
//		registrar el MessageDispatcherServlet
//		registrar el DefaultWsdl11Definition
//		com.curso.configuracion.ConfiguracionSOAP


@SpringBootApplication
public class Aplicacion {

	public static void main(String[] args) {
		SpringApplication.run(Aplicacion.class, args);
	}

}
