package com.curso.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.curso.soap.cliente.ClienteSoapClient;

@Configuration
public class ClienteSoapConfig {

    @Bean
    Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.curso.webservice.clientes");
        return marshaller;
    }

    @Bean
    ClienteSoapClient clienteSoapClient(Jaxb2Marshaller marshaller) {
        ClienteSoapClient client = new ClienteSoapClient();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}