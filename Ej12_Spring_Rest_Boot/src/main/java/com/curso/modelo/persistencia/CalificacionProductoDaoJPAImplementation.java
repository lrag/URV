package com.curso.modelo.persistencia;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.curso.modelo.entidad.CalificacionProducto;
import com.curso.modelo.entidad.Producto;

@Repository
public class CalificacionProductoDaoJPAImplementation extends AbstractJPADao<CalificacionProducto, Integer> implements CalificacionProductoDao {

	@Override
	public List<CalificacionProducto> listarPorProducto(Producto producto) {
		
		System.out.println("DAO: "+producto);
		List x = em.createQuery("select cp from CalificacionProducto cp where cp.producto.id=:idProducto").setParameter("idProducto", producto.getId()).getResultList();
		System.out.println("DAO: "+x);
		return x;
	}
	
}

