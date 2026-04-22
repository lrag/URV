package com.curso.modelo.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.Producto;
import com.curso.modelo.persistencia.ProductoRepositorio;

@Component
public class ProductosRestProxy {

	//RestTemplate: Síncrona, imperativa. Es la antígua
	//RestClient: Síncrona, declarativa. Es la que hay que utilizar
	//WebClient: Cliente REACTIVO
	
	@Autowired private RestClient restClient;	
	@Autowired private ProductoRepositorio productoRepo;
	
	public Producto buscar(String codigo){
		
		return productoRepo
				.findByCodigo(codigo)
				.orElseGet( () -> {
					System.out.println("Invocando al microservicio de productos");
					//No estamos controlando los posibles errores al enviar la petición!!!
					
					Producto prodAux = restClient
						.get()
						//.uri("http://localhost:9020/productos/"+codigo)
						.uri("http://ServicioProductos/productos/"+codigo)
						.retrieve()
						.body(Producto.class);
					
					System.out.println("Producto obtenido:"+prodAux);
					productoRepo.save(prodAux);
					return prodAux;
				});				
	}
	
}
