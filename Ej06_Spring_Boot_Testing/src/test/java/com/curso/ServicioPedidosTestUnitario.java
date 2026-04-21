package com.curso;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.DetallePedido;
import com.curso.modelo.entidad.Pedido;
import com.curso.modelo.entidad.Producto;
import com.curso.modelo.negocio.ServicioOfertas;
import com.curso.modelo.negocio.ServicioPedidos;
import com.curso.modelo.persistencia.PedidoRepositorio;
import com.curso.modelo.proxy.ClientesRestProxy;
import com.curso.modelo.proxy.ProductosRestProxy;

@SpringBootTest
//Para ejecutar un test de Spring Boot se crea un contenedor de Spring
class ServicioPedidosTestUnitario {

	//Desde Spring Boot 3.4 @MockBean está marcada como deprecated y sustituida por @MockitoBean
	@MockitoBean PedidoRepositorio pedidoRepositorio;
	@MockitoBean ClientesRestProxy clientesRestProxy;
	@MockitoBean ProductosRestProxy productosRestProxy;
	@MockitoBean ServicioOfertas servicioOfertas;
	
	@Autowired
	//Este es el objeto real que queremos probar
	ServicioPedidos servicioPedidos;

	
	public ServicioPedidosTestUnitario() {
		super();
		System.out.println("Creando ServicioPedidosTestUnitario");
	}

	@Test
	///////////////////
	//@DirtiesContext//<-- indica si necesitamos otro contenedor de spring después de este test
	///////////////////
	void aceptarPedido() throws Exception {
		
		System.out.println(">>TEST 1>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		//DADOS ESTOS DATOS
		Cliente c1 = new Cliente();
		c1.setLogin("ringo@starr.com");
		
		Producto pr1 = new Producto();
		pr1.setCodigo("PROD-1");
		Producto pr2 = new Producto();
		pr2.setCodigo("PROD-2");
		
		List<DetallePedido> detalles1 = new ArrayList<>();
		Pedido pedidoRecibido = new Pedido(1,null,"FECHA", "PENDIENTE", 0d, c1, detalles1);
		detalles1.add(new DetallePedido(1, 10d, 1, pedidoRecibido, pr1));
		detalles1.add(new DetallePedido(2, 20d, 1, pedidoRecibido, pr2));

		//ESTOS TEST DOUBLES
		
		//PedidoRepositorio es un DUMMIE	
		
		Mockito
			.when(clientesRestProxy.buscar(c1.getLogin()))
			.thenReturn(new Cliente(1,"ringo@starr.com", "Ringo Starr", "1234"));
		
		Mockito
			.when(productosRestProxy.buscar(pr1.getCodigo()))
			.thenReturn(new Producto(1, "PROD-1", "Chisme", 10d));	
		
		Mockito
			.when(productosRestProxy.buscar(pr2.getCodigo()))
			.thenReturn(new Producto(2, "PROD-2", "Fleje", 20d));	

		//Y UN GESTOR_PEDIDOS
		
		//CUANDO
		Pedido pedidoAlta = servicioPedidos.altaPedido(pedidoRecibido);
		
		//ENTONCES
		Assertions.assertAll(
				() -> Assertions.assertNotNull(pedidoAlta.getCodigo()),
				() -> Assertions.assertEquals(30, pedidoAlta.getTotal(), "Error al calcular el total del pedido"),
				() -> Assertions.assertEquals("Ringo Starr", pedidoAlta.getCliente().getNombre() ),
				() -> Assertions.assertEquals("Chisme", pedidoAlta.getDetalles().get(0).getProducto().getNombre() ),
				() -> Assertions.assertEquals("Fleje", pedidoAlta.getDetalles().get(1).getProducto().getNombre())
			);	
	}
	
	@Test
	public void test2() {
		System.out.println(">>TEST 2>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
}
