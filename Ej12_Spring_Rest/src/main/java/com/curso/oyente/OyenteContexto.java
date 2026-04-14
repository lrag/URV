package com.curso.oyente;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class OyenteContexto implements ServletContextListener {

    public OyenteContexto() {
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("APLICACIÓN LEVANTADA!!!!");
    }
    
    public void contextDestroyed(ServletContextEvent sce)  { 
    	
    }

}
