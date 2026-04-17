package com.curso.modelo.negocio;

import java.util.List;
import com.curso.rest.ControladorExcepciones_Swagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.curso.modelo.entidad.CalificacionProducto;
import com.curso.modelo.entidad.Producto;
import com.curso.modelo.persistencia.CalificacionProductoDao;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class ServicioCalificacionesProducto {

	@Autowired
	private CalificacionProductoDao calificacionProductoDao;
	
	@Transactional()
	public void insertar(CalificacionProducto obj) {
		//LN
		calificacionProductoDao.insertar(obj);
	}

	public void modificar(CalificacionProducto obj) {
		//LN
	}

	public void borrar(CalificacionProducto obj) {
		//LN
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public CalificacionProducto buscar(Integer id) {
		return null;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<CalificacionProducto> listarPorProducto(Producto producto) {
		return calificacionProductoDao.listarPorProducto(producto);
	}

}
