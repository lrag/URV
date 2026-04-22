package com.curso.rest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.ServicioClientes;
import com.curso.modelo.negocio.excepcion.ClienteException;
import com.curso.rest.dto.ClienteDto;
import com.curso.rest.dto.Data;
import com.curso.rest.dto.Respuesta;
import com.curso.rest.dto.RespuestaError;
import com.curso.rest.dto.RespuestaOk;
import com.curso.rest.dto.Zasca;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/*
GET    /clientes
GET    /clientes/id
POST   /clientes
PUT    /clientes/id
DELETE /clientes/id
*/

@RestController
@RequestMapping(
		path = "/clientes",
		produces = { "application/json", "application/xml" }
	)
public class ClientesRest {

	private ServicioClientes gestorClientes;
	
	public ClientesRest(ServicioClientes gestorClientes) {
		super();
		this.gestorClientes = gestorClientes;
	}
	
	//Para HEAD, OPTIONS y TRACE no hay anotación específica 
	@RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
	public void HEAD() {
		
	}
	
	@GetMapping(path = "/padilla")
	public String x(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, ServletContext svCtx, HttpServletRequest request2) {
		return "TOCOTO";
	}
	
	@PostMapping(
			consumes = { "application/json", "application/xml" }
		)
	public ResponseEntity<Respuesta> insertar(@Valid @RequestBody ClienteDto clienteDto) throws ClienteException {
		
		Cliente cliente = clienteDto.asCliente();
		gestorClientes.insertar(cliente);
		
		Data data = new Data("Cliente insertado", new ClienteDto(cliente));
		RespuestaOk r = new RespuestaOk("201","SUCCESS", data);
		return new ResponseEntity<>(r, HttpStatus.CREATED);		
	}

	@PutMapping( 
			path = "/{id}",
			consumes = { "application/json", "application/xml" }
		)
	public ResponseEntity<Respuesta> modificar(@PathVariable("id") Integer id, @Valid @RequestBody ClienteDto clienteDto) throws ClienteException {
		
		Cliente cliente = clienteDto.asCliente();
		cliente.setId(id);
		
		gestorClientes.modificar(cliente);
		
		Data data = new Data("Cliente insertado", new ClienteDto(cliente));
		RespuestaOk r = new RespuestaOk("200","SUCCESS", data);
		return new ResponseEntity<>(r, HttpStatus.OK);		
	}	
	
	@DeleteMapping( path = "/{id}")
	public void borrar(@PathVariable("id") Integer id) {
		int a = 10 / 0;
		System.out.println(a);
	}
	
	@GetMapping( path = "/{id}")
	public ResponseEntity<Respuesta> buscar(@PathVariable("id") Integer id) {
		Cliente cliente = gestorClientes.buscar(id);
		if(cliente == null) {
			Zasca error = new Zasca("404", "El cliente "+id+" no existe.");
			RespuestaError r = new RespuestaError("404","ERROR", error);
			return new ResponseEntity<>(r, HttpStatus.NOT_FOUND);				
		}
		
		Data data = new Data("Cliente encontrado", new ClienteDto(cliente));
		RespuestaOk r = new RespuestaOk("200","SUCCESS", data);
		return new ResponseEntity<>(r, HttpStatus.OK);			
	}
	
	@GetMapping
	public ResponseEntity<Respuesta> listar() {		
		List<ClienteDto> clientesDto = gestorClientes.listar()
				.stream()
				.map(c -> new ClienteDto(c))
				.collect(Collectors.toList());
		
		Data data = new Data("Listado de clientes", clientesDto);
		RespuestaOk r = new RespuestaOk("200","SUCCESS", data);
		return new ResponseEntity<>(r, HttpStatus.OK);			
	}
	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<? extends Respuesta> handleValidationException(MethodArgumentNotValidException e) {
		Map<String, String> errores = 
				e.getBindingResult()
				.getFieldErrors()
				.stream()
				.collect(Collectors.toMap( fe -> (String) fe.getField(), 
								           fe -> (String) fe.getDefaultMessage()));
		
		Zasca error = new Zasca("400", "Datos inválidos", errores);
		RespuestaError r = new RespuestaError("400","ERROR", error);
		
		return new ResponseEntity<RespuestaError>(r, HttpStatus.BAD_REQUEST);		
	}
	
}
