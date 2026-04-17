package com.curso.modelo.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.curso.modelo.entidad.Avion;

public class ServicioAviones {

	private static List<Avion> aviones;
	private static Integer contador;
	
	static {
		aviones = new ArrayList<>();
		aviones.add(new Avion(1,"Grumman","F-14",1974));
		aviones.add(new Avion(2,"MIG","29",1983));
		aviones.add(new Avion(3,"Panavia","Tornado",1979));
		aviones.add(new Avion(4,"McDonell Douglas","F-15",1976));
		aviones.add(new Avion(5,"McDonell Douglas","F-18",1983));
		contador = 5;	
	}
	
	public void insertar(Avion avion) throws Exception {
		if(avion.getFabricante() == null) {
			throw new Exception("Error de validacion");
		}
		
		avion.setId(++contador);
		aviones.add(avion);
	}
	
	public void borrar(Avion avion) {
		for(Avion a: aviones) {
			if( a.getId() == avion.getId() ) {
				aviones.remove(a);
				break;
			}
		}
	}
	
	public Avion buscar(Integer id) {
		return aviones
			.stream()
			.filter( a -> a.getId()==id )
			.findFirst()
			.orElse(null);
	}
	
	public List<Avion> listar(){
		return aviones;
	}
	
	public List<Avion> listarPorFabricante(String fabricante){
		return aviones
			.stream()
			.filter( a -> a.getFabricante().equalsIgnoreCase(fabricante))
			.collect(Collectors.toList());
	}
		
}
