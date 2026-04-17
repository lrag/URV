package com.curso.modelo.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.curso.modelo.entidad.Producto;

@Repository
public class ProductoDaoJPAImplementation implements ProductoDao {

	//JPA tiene una anotación específica de JEE para inyectar el EntityManager
	//En spring tambien se usa
	//@Autowired
	@PersistenceContext//(unitName="default")
	private EntityManager em;
		
	@Override
	public void insertar(Producto obj) {
		//EntityManagerFactory emf = null;
		//EntityManager em = emf.createEntityManager();
		//em.getTransaction().begin();		
		em.persist(obj);
		//em.getTransaction().commit();
		//em.close();
	}

	@Override
	public void modificar(Producto obj) {
		em.merge(obj);
	}

	@Override
	public void borrar(Producto obj) {
		Producto pAux = em.find(Producto.class, obj.getId());
		em.remove(pAux);
	}

	@Override
	public Producto buscar(Integer id) {
		return em.find(Producto.class, id);
	}

	@Override
	public List<Producto> listar() {
		Query q = em.createQuery("select p from Producto p");
		return q.getResultList();
	}

}








