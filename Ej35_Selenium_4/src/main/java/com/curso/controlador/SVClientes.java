package com.curso.controlador;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.ServicioClientes;

@WebServlet("/seguro/SVClientes")
public class SVClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ServicioClientes gestorClientes = new ServicioClientes();
	
    public SVClientes() {
        super();
    }
    
    //GET usado para lo sigiente
    //Cancelar (verListado)
    //Nuevo, Vaciar (verFormulario)
    //Seleccionar.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sesion = request.getSession(true);
		String accion = request.getParameter("accion");
		
		String siguienteVista = "listadoClientes.jsp";
		//Si pulsamos el boton nuevo
		if("verFormulario".equals(accion)){			
			siguienteVista = "formularioClientes.jsp";
		} else if("seleccionar".equals(accion)){	
			Cliente clienteSel = gestorClientes.buscar(Integer.parseInt(request.getParameter("idCliente")));
			request.setAttribute("clienteSel", clienteSel);			
			siguienteVista = "formularioClientes.jsp";
		} 	
		
		if(siguienteVista.equals("listadoClientes.jsp")){
			List<Cliente> clientes = gestorClientes.listar(0,20);
			request.setAttribute("listadoClientes", clientes);
		} else if(siguienteVista.equals("formularioClientes.jsp")){
			//
		}		
		
		request.getRequestDispatcher(siguienteVista).
			forward(request,response);		
	}
	
	//POST usado para lo siguiente
	//Insertar
	//Modificar
	//Borrar
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("idCliente"));
		} catch (NumberFormatException e) {
		}
				
		//Habría que impedir inyecciones (XSS, HTML, etc)
		String nombre = request.getParameter("nombre");
		String direccion = request.getParameter("direccion");
		String telefono = request.getParameter("telefono");
		
		//Construir objetos del modelo con los parametros recibidos
		Cliente p = new Cliente(id, nombre, direccion, telefono);
		
		//Validar los objetos
		//...
		
		//Averiguar que nos están pidiendo
		String accion = request.getParameter("accion");
		//Llamar al método de negocio adecuado segun la accion
		if("insertar".equals(accion)){
			gestorClientes.insertar(p);
		} else if("modificar".equals(accion)){
			gestorClientes.modificar(p);
		} else if("borrar".equals(accion)){
			gestorClientes.borrar(p);
		}
		
		//Despues de una petición post se hace un redirect.
		//Si se está usando MVC no se puede hacer un redirect a una vista
		//Se hace un redirect al controlador que muestre la vista que nos 
		//interesa
		//GET SVClientes
		response.sendRedirect("SVClientes");
	
	}

}





