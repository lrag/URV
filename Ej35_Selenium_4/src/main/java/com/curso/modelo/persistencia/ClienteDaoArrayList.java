package com.curso.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import com.curso.modelo.entidad.Cliente;

//
//Una implementaci�n que da el pego
//
public class ClienteDaoArrayList implements ClienteDao {

	private static final List<Cliente> clientes;
	private static int contador;
	
	static {
		clientes = new ArrayList<Cliente>();
		clientes.add(new Cliente(1,"Félix","C/Tal","555123"));
		clientes.add(new Cliente(2,"LRA","C/Pascual","555444"));
		clientes.add(new Cliente(3,"Ángel","C/Tal y pascual","555333"));
		clientes.add(new Cliente(3,"Borja","C/Pascualillo","555555"));
		contador = clientes.size();
	}
	
	public void insertar(Cliente cliente){
		contador++;
		cliente.setId(contador);
		clientes.add(cliente);
	}
	
	public void modificar(Cliente cliente){
		for(int a=0; a<clientes.size(); a++) {
			Cliente cAux = clientes.get(a);
			if(cAux.getId() == cliente.getId()){
				clientes.set(a, cliente);
				break;
			}
		}
	}
	
	public void borrar(Cliente cliente){
		for(int a=0; a<clientes.size(); a++) {
			Cliente cAux = clientes.get(a);
			if(cAux.getId() == cliente.getId()){
				clientes.remove(a);
				break;
			}
		}		
	}
	
	public Cliente buscar(int id){
		return clientes
			.stream()
			.filter(c -> c.getId()==id)
			.findFirst()
			.orElse(null);
	}
	
	public List<Cliente> listar(int primero, int cantidad){
		return clientes;
	}
	
}
