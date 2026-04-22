package com.curso.modelo.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.curso.modelo.entidad.Pedido;

@Repository
public class PedidoDaoJPAImplementation implements PedidoDao{
	//JPA tiene una anotacion especifica de JEE para inyectar el EntityManager
	//Spring tambien lo usa
	//@Autowired
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void insertar(Pedido obj) {
		em.persist(obj);
	}

	@Override
	public void modificar(Pedido obj) {
		em.merge(obj);
	}

	@Override
	public void borrar(Pedido obj) {
		em.remove(em.find(Pedido.class, obj.getId()));
	}

	@Override
	public Pedido buscar(Integer id) {
		return em.find(Pedido.class, id);
	}

	@Override
	public List<Pedido> listar() {
		TypedQuery<Pedido> q = em.createQuery("select ped from Pedido ped",Pedido.class);
		return q.getResultList();
	}

}
