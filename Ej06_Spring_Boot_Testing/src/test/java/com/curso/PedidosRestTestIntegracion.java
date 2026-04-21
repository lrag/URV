package com.curso;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.DetallePedido;
import com.curso.modelo.entidad.Pedido;
import com.curso.modelo.entidad.Producto;
import com.curso.modelo.proxy.ClientesRestProxy;
import com.curso.modelo.proxy.ProductosRestProxy;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class PedidosRestTestIntegracion {

	//Para probar los Endpoints 
	@Autowired MockMvc mockMvc;
	
	//Test doubles
	@MockitoBean ClientesRestProxy clientesRestProxy;
	@MockitoBean ProductosRestProxy productosRestProxy;	
	
	//Este test tiene todo real excepto los Clientes de las aplicaciones remotas
	
	//Objecto REAL
	@Autowired ObjectMapper objectMapper;	

	@Test
	void listarPedidos() throws Exception {			
		mockMvc
			.perform(get("/pedidos"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", Matchers.hasSize(1))) //Hamcrest
			.andExpect(jsonPath("$[0].codigo", Matchers.is("PED-1655393408")));
	}
	
	//@Test
	void buscarPedido() throws Exception {
		mockMvc
			.perform(get("/pedidos/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.codigo", Matchers.is("PED-1655393408")));			
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
