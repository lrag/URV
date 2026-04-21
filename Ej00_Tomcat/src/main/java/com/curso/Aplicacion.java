package com.curso;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import com.curso.controlador.SVHora;

public class Aplicacion {

	public static void main(String[] args) throws LifecycleException {
		
		Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector(); 
        String docBase = new File(".").getAbsolutePath();
        Context context = tomcat.addContext("", docBase);
        
        SVHora svHora = new SVHora();
        Tomcat.addServlet(context, "SVHora", svHora);
        context.addServletMappingDecoded("/hora", "SVHora");
        
        tomcat.start();
        tomcat.getServer().await();      
		
	}
	
}
