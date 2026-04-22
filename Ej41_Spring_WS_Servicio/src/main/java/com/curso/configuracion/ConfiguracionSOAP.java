package com.curso.configuracion;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class ConfiguracionSOAP {

    @Bean
    //El starter no registra el Front Controller
    ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    // Aquí es donde se define el nombre del WSDL (será clientes.wsdl)
    /*
    En src/main/resources/xml/clientes.xsd:
    
	<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema" 
           xmlns:tns="http://curso.com/webservice/clientes"
           targetNamespace="http://curso.com/webservice/clientes" <---
           elementFormDefault="qualified">    
    */
    @Bean(name = "clientes")
    DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema clientesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ClientesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://curso.com/webservice/clientes");
        wsdl11Definition.setSchema(clientesSchema);
        return wsdl11Definition;
    }

    @Bean
    XsdSchema clientesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xml/clientes.xsd"));
    }
}