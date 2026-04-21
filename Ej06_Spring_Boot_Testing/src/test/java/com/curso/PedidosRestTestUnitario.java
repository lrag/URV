package com.curso;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.curso.endpoint.PedidosRest;
import com.curso.endpoint.dto.PedidoDTO;
import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.DetallePedido;
import com.curso.modelo.entidad.Pedido;
import com.curso.modelo.entidad.Producto;
import com.curso.modelo.negocio.ServicioPedidos;
import com.curso.modelo.persistencia.PedidoRepositorio;
import com.curso.modelo.proxy.ClientesRestProxy;
import com.curso.modelo.proxy.ProductosRestProxy;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class PedidosRestTestUnitario {

	//Test doubles
	@MockitoBean ClientesRestProxy clientesRestProxy;
	@MockitoBean ProductosRestProxy productosRestProxy;	
		
	@MockitoBean PedidoRepositorio pedidoRepositorio; 
	@MockitoBean ServicioPedidos servicioPedidos;
	
	//Objecto REAL
	@Autowired ObjectMapper objectMapper;	

	/*
	No tiene sentido hacer un test unitario de un endpoint como si este fuera un objeto de negocio
	@Autowired PedidosRest pedidosRest;
	
	@Test
	public void pruebaInsertar() {
		//Creamos aqui lo que Spring MVC extraerá de la petición HTTP
		PedidoDTO pedidoDTO = new PedidoDTO(DATOS);
		//Llamamos a un método que no tienen lógica de negocio!
		ResponseEntity<PedidoDTO> respuesta =  pedidosRest.insertar(pedidoDTO);
		//Hacemos asertos contra un ResponseEntity???
		Assertions.assertNotNull(respuesta);
	}
	*/
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void listarPedidos() throws Exception {
		
		Cliente c1 = new Cliente(1,"cli1@correo.es", "Ringo Starr", "1234");
		Cliente c2 = new Cliente(2,"cli2@correo.es", "Harry Callahan", "4321");
		Producto pr1 = new Producto(1, "PROD-1", "Chisme", 10d);
		Producto pr2 = new Producto(2, "PROD-2", "Fleje", 20d);
		Producto pr3 = new Producto(3, "PROD-3", "Elemento disrruptor", 30d);
		
		List<DetallePedido> detalles1 = new ArrayList<>();
		Pedido p1 = new Pedido(1,"PED-10","FECHA 1", "PENDIENTE", 50d, c1, detalles1);
		detalles1.add(new DetallePedido(1, 10d, 1, p1, pr1));
		detalles1.add(new DetallePedido(2, 20d, 1, p1, pr2));
		detalles1.add(new DetallePedido(3, 30d, 1, p1, pr3));

		List<DetallePedido> detalles2 = new ArrayList<>();
		Pedido p2 = new Pedido(2,"PED-20","FECHA 2", "ACEPTADO", 40d, c2, detalles2);
		detalles1.add(new DetallePedido(1, 10d, 2, p2, pr1));
		detalles1.add(new DetallePedido(2, 20d, 1, p2, pr2));
		
		List<Pedido> pedidos = new ArrayList<>();
		pedidos.add(p1);
		pedidos.add(p2);
		
		Mockito.when(pedidoRepositorio.findAll()).thenReturn(pedidos);
		
		mockMvc
			.perform(get("/pedidos"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", Matchers.hasSize(2))) //Hamcrest
			.andExpect(jsonPath("$[0].codigo", Matchers.is("PED-10")))	
			.andExpect(jsonPath("$[1].codigo", Matchers.is("PED-20")));			
	}
	
	@Test
	void buscarPedido() throws Exception {
		
		Cliente c1 = new Cliente(1,"cli1@correo.es", "Ringo Starr", "1234");
		Producto pr1 = new Producto(1, "PROD-1", "Chisme", 10d);
		Producto pr2 = new Producto(2, "PROD-2", "Fleje", 20d);
		Producto pr3 = new Producto(3, "PROD-3", "Elemento disrruptor", 30d);
		
		List<DetallePedido> detalles1 = new ArrayList<>();
		Pedido p1 = new Pedido(1,"PED-10","FECHA 1", "PENDIENTE", 50d, c1, detalles1);
		detalles1.add(new DetallePedido(1, 10d, 1, p1, pr1));
		detalles1.add(new DetallePedido(2, 20d, 1, p1, pr2));
		detalles1.add(new DetallePedido(3, 30d, 1, p1, pr3));
		
		Mockito.when(pedidoRepositorio.buscarPedidosConDetalles(Mockito.anyInt())).thenReturn(Optional.of(p1));
		
		mockMvc
			.perform(get("/pedidos/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.codigo", Matchers.is("PED-10")));			
	}	
	
	@Test
	void altaPedido() throws Exception {
		
		Cliente c1 = new Cliente();
		c1.setLogin("ringo@starr.com");
		
		Producto pr1 = new Producto();
		pr1.setCodigo("PROD-1");
		Producto pr2 = new Producto();
		pr2.setCodigo("PROD-2");
		
		List<DetallePedido> detalles1 = new ArrayList<>();
		Pedido pedidoRecibido = new Pedido(1,null,"FECHA", "PENDIENTE", 0d, c1, detalles1);
		detalles1.add(new DetallePedido(1, 10d, 1, null, pr1));
		detalles1.add(new DetallePedido(2, 20d, 1, null, pr2));	
		
		Mockito
			.when(clientesRestProxy.buscar(c1.getLogin()))
			.thenReturn(new Cliente(1,"ringo@starr.com", "Ringo Starr", "1234"));
		
		Mockito
			.when(productosRestProxy.buscar(pr1.getCodigo()))
			.thenReturn(new Producto(1, "PROD-1", "Chisme", 10d));	
		Mockito
			.when(productosRestProxy.buscar(pr2.getCodigo()))
			.thenReturn(new Producto(2, "PROD-2", "Fleje", 20d));			
		
		mockMvc
			.perform(post("/pedidos")
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(pedidoRecibido)))
			.andExpect(status().isCreated());			
	}		

}
