package com.curso.endpoint;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.modelo.entidad.Producto;

@RestController
@RequestMapping("/productos")
public class ProductosRest {

	@GetMapping()
	public List<Producto> listar() {
		
		return List.of(
				new Producto(1, "PR-1", "P1", 10d),
				new Producto(2, "PR-2", "P2", 20d),
				new Producto(3, "PR-3", "P3", 30d),
				new Producto(4, "PR-4", "P4", 40d),
				new Producto(5, "PR-5", "P5", 50d)
			);
		
	}
	
	
}
