package com.curso;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.modelo.entidad.CalificacionProducto;
import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.DetallePedido;
import com.curso.modelo.entidad.Pedido;
import com.curso.modelo.entidad.Producto;
import com.curso.modelo.persistencia.CalificacionProductoDao;
import com.curso.modelo.persistencia.ProductoDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@SpringBootApplication
public class Aplicacion implements CommandLineRunner{

	@Autowired private ProductoDao pDao;
	@Autowired private CalificacionProductoDao cpDao;
	@Autowired private EntityManagerFactory emf;
	
	
	public static void main(String[] args) {
		SpringApplication.run(Aplicacion.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Producto p1 = new Producto(null, "P1", "F1", 25d, 1000);
		Producto p2 = new Producto(null, "P2", "F2", 50d, 1000);
		Producto p3 = new Producto(null, "P3", "F3", 75d, 1000);
		Producto p4 = new Producto(null, "P4", "F4", 100d, 1000);
		Producto p5 = new Producto(null, "P5", "F5", 125d, 1000);
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);		
		em.persist(p4);
		em.persist(p5);
		
		CalificacionProducto cp1 = new CalificacionProducto(null, "Bartolo", "Está bien", p1);
		CalificacionProducto cp2 = new CalificacionProducto(null, "Bartola", "Está bien", p1);
		CalificacionProducto cp3 = new CalificacionProducto(null, "Ringo Starr", "Está muy bien", p1);
		CalificacionProducto cp4 = new CalificacionProducto(null, "Ellen Ripley", "No está mal", p1);
		CalificacionProducto cp5 = new CalificacionProducto(null, "Higinio Alonso", "8/10", p1);
		em.persist(cp1);
		em.persist(cp2);
		em.persist(cp3);
		em.persist(cp4);
		em.persist(cp5);
		
		Cliente c1 = new Cliente(null, "Harpo", "Su casa", "123", 1);
		Cliente c2 = new Cliente(null, "Mongomery Burns", "Su mansión", "123", 9999);
		
		Pedido pedido1 = new Pedido(null, "PED-0", LocalDate.now(), "PENDIENTE", c1, null);
		List<DetallePedido> detalles1 = new ArrayList<DetallePedido>();
		detalles1.add(new DetallePedido(null, pedido1, p2, 25d, 25));
		detalles1.add(new DetallePedido(null, pedido1, p4, 25d, 25));
		pedido1.setDetalles(detalles1);

		Pedido pedido2 = new Pedido(null, "PED-1", LocalDate.now(), "PENDIENTE", c2, null);
		List<DetallePedido> detalles2 = new ArrayList<DetallePedido>();
		detalles2.add(new DetallePedido(null, pedido2, p1, 25d, 25));
		detalles2.add(new DetallePedido(null, pedido2, p3, 75d, 25));
		detalles2.add(new DetallePedido(null, pedido2, p5, 125d, 25));
		pedido2.setDetalles(detalles2);

		Pedido pedido3 = new Pedido(null, "PED-1", LocalDate.now(), "PENDIENTE", c2, null);
		List<DetallePedido> detalles3 = new ArrayList<DetallePedido>();
		detalles3.add(new DetallePedido(null, pedido3, p1, 25d, 25));
		detalles3.add(new DetallePedido(null, pedido3, p3, 75d, 25));
		detalles3.add(new DetallePedido(null, pedido3, p5, 125d, 2500));
		pedido3.setDetalles(detalles3);

		em.persist(c1);
		em.persist(c2);
		
		em.persist(pedido1); //Id: 1
		em.persist(pedido2); //Id: 2
		em.persist(pedido3); //Id: 3
		
		em.getTransaction().commit();
		em.close();
		*/
		
		System.out.println(pDao.listar());
		Producto pAux = pDao.buscar(1);
		System.out.println(pAux);
		System.out.println(cpDao.listarPorProducto(pAux));
		System.out.println(cpDao.listar());
		
	}

}
