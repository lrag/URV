package com.curso.controlador;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hora")
public class SVHora extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response ) throws IOException {
		response.getWriter().write("Hora: "+LocalDateTime.now());
	}
	
}
