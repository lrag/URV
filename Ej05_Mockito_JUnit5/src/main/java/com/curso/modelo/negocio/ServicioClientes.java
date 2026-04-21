package com.curso.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.Comercial;
import com.curso.modelo.entidad.Sucursal;
import com.curso.modelo.persistencia.ClienteDao;
import com.curso.modelo.util.EmisorCorreosElectronicos;

public class ServicioClientes {

	private ClienteDao clienteDao;
	private ServicioSucursales servicioSucursales;
	private ServicioComerciales servicioComerciales;
	private ServicioDirecciones servicioDirecciones;
	private EmisorCorreosElectronicos emisorCorreos;

	public void setClienteDao(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}

	public void setGestorSucursales(ServicioSucursales gestorSucursales) {
		this.servicioSucursales = gestorSucursales;
	}

	public void setGestorComerciales(ServicioComerciales gestorComerciales) {
		this.servicioComerciales = gestorComerciales;
	}

	public void setGestorDirecciones(ServicioDirecciones gestorDirecciones) {
		this.servicioDirecciones = gestorDirecciones;
	}
	
	public void setEmisorCorreos(EmisorCorreosElectronicos emisorCorreos) {
		this.emisorCorreos = emisorCorreos;
	}

	// Recibe el cliente por referencia, lo modifica y lo devuelve
	public Cliente altaCliente(Cliente cliente) throws Exception {

		// ...

		servicioDirecciones.comprobarDireccion(cliente.getDireccion()); //Lanza excepción si la direccionno existe

		// ...

		Sucursal sucursal = servicioSucursales.encontrarSucursalCercana(cliente.getDireccion()); //Devuelve null si no hay sucursal
		if (sucursal == null) {
			sucursal = new Sucursal(1, "Sucursal virtual", "www.sucursal.es");
		}

		// ...

		cliente.setSucursal(sucursal);

		// ...

		List<Comercial> comerciales = servicioComerciales.encontrarComerciales();
		cliente.setComerciales(comerciales);
		
		// ...
		
		cliente = clienteDao.insertar(cliente);

		// ...
		
		emisorCorreos.enviarCorreo("", cliente.getDireccion());

		return cliente;
	}

	// Recibimos una lista de clientes e intentamos insertarlos
	// Si hay algún cliente que no se puede insertar el proceso NO se detiene
	public void altaClientes(List<Cliente> clientes) {
		List<Cliente> clientesNoInsertados = new ArrayList<>();
		for (Cliente c : clientes) {
			try {
				altaCliente(c);
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
				clientesNoInsertados.add(c);
			}
		}
		//return clientesNoInsertados;
	}

	// En los test doubles solo aparecen los métodos PUBLICOS
	private void metodo() {
		// c�digo
	}

}


//UN DUMMIE DE LA CLASE GestorCLientes:
/*
class ServicioClientes_Dummie extends ServicioClientes {

	public void setClienteDao(ClienteDao clienteDao) { }

	public void setGestorSucursales(ServicioSucursales gestorSucursales) { }

	public void setGestorComerciales(ServicioComerciales gestorComerciales) { }

	public void setGestorDirecciones(ServicioDirecciones gestorDirecciones) { }

	public Cliente altaCliente(Cliente cliente) throws Exception { return null; }

	public void altaClientes(List<Cliente> clientes){ }

}
*/








