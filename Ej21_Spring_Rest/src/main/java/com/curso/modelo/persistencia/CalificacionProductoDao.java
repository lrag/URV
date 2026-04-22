package com.curso.modelo.persistencia;

import java.util.List;

import com.curso.modelo.entidad.CalificacionProducto;
import com.curso.modelo.entidad.Producto;

public interface CalificacionProductoDao extends InterfaceDao<CalificacionProducto, Integer>{
	
	List<CalificacionProducto> listarPorProducto(Producto producto);
	
}
