package com.curso.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.modelo.entidad.Producto;
import com.curso.modelo.negocio.ServicioCalificacionesProducto;
import com.curso.modelo.negocio.ServicioProductos;
import com.curso.rest.dto.CalificacionProductoDto;
import com.curso.rest.dto.Data;
import com.curso.rest.dto.ProductoDto;
import com.curso.rest.dto.Respuesta;
import com.curso.rest.dto.RespuestaError;
import com.curso.rest.dto.RespuestaOk;
import com.curso.rest.dto.Zasca;

import jakarta.validation.Valid;

@RestController
@RequestMapping(
		path = "/productos",
		produces = { "application/json", "application/xml" }
	)
public class ProductosRest {

	private ServicioProductos servicioProductos;
	private ServicioCalificacionesProducto servicioCalificaciones;

	public ProductosRest(ServicioProductos servicioProductos, ServicioCalificacionesProducto servicioCalificaciones) {
		super();
		this.servicioProductos = servicioProductos;
		this.servicioCalificaciones = servicioCalificaciones;
	}	

	//GET /productos/{id}
	@GetMapping("/{idProducto}")
	public ResponseEntity<Respuesta> buscarProducto(@PathVariable("idProducto") Integer idProducto){
		Producto producto = servicioProductos.buscar(idProducto);
		if(producto == null) {
			Zasca error = new Zasca("404", "El producto "+idProducto+" no existe.");
			RespuestaError r = new RespuestaError("404","ERROR", error);
			return new ResponseEntity<>(r, HttpStatus.NOT_FOUND);				
		}
		
		Data data = new Data("Producto encontrado", new ProductoDto(producto));
		RespuestaOk r = new RespuestaOk("200","SUCCESS", data);
		return new ResponseEntity<>(r, HttpStatus.OK);				
	}
	
	@GetMapping()
	public ResponseEntity<Respuesta> listarProductos(){
		List<ProductoDto> productosDto = servicioProductos
				.listar()
				.stream()
				.map( producto -> new ProductoDto(producto))
				.toList();
		
		Data data = new Data("Listado de productos", productosDto);
		RespuestaOk r = new RespuestaOk("200","SUCCESS", data);
		return new ResponseEntity<>(r, HttpStatus.OK);				
	}
	
	//POST /productos
	@PostMapping( consumes = { "application/json", "application/xml" } )
	public ResponseEntity<Respuesta> insertarProducto(@Valid @RequestBody ProductoDto productoDto){		
		Producto producto = productoDto.asProducto();
		servicioProductos.insertar(producto);
		Data data = new Data("Producto insertado", new ProductoDto(producto));
		RespuestaOk r = new RespuestaOk("201","CREATED", data);
		return new ResponseEntity<>(r, HttpStatus.OK);	
	}
	
	//GET /productos/1/calificaciones
	@GetMapping("/{idProducto}/calificaciones")
	public ResponseEntity<Respuesta> buscarCalificacionesProducto(@PathVariable("idProducto") Integer idProducto){
		Producto producto = new Producto();
		producto.setId(idProducto);
		List<CalificacionProductoDto> calificaciones = servicioCalificaciones
				.listarPorProducto(producto)
				.stream()
				.map( cp -> new CalificacionProductoDto(cp))
				.toList();
		
		
		System.out.println("CALIFICACIONES: "+calificaciones);
		
		Data data = new Data("Calificaciones del producto", calificaciones);
		RespuestaOk r = new RespuestaOk("200","SUCCESS", data);
		return new ResponseEntity<>(r, HttpStatus.OK);	
	}
	
	//POST /productos/{idProducto}/calificaciones
	public ResponseEntity<Respuesta> insertarCalificacionProducto(){		
		return null;	
	}	
	
}
