package com.curso.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.GestorClientes;
import com.curso.modelo.negocio.excepcion.ClienteException;
import com.curso.rest.dto.ClienteDto;
import com.curso.rest.dto.Data;
import com.curso.rest.dto.Respuesta;
import com.curso.rest.dto.RespuestaError;
import com.curso.rest.dto.RespuestaOk;
import com.curso.rest.dto.Zasca;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


//
//
// http://localhost:8080/Ej12_Spring_Rest/v3/api-docs.yaml
//
//

//@RestController
@RequestMapping(
		path = "/clientes",
		produces = { "application/json", "application/xml" }
	)
@Tag(name = "Cliente", description = "API Rest para la gestión de clientes")
public class ClientesRest_Swagger {

	private GestorClientes gestorClientes;
	
	public ClientesRest_Swagger(GestorClientes gestorClientes) {
		super();
		this.gestorClientes = gestorClientes;
	}
	
	@PostMapping(consumes = { "application/json", "application/xml" })
	@Operation(summary = "Alta de cliente", description = "Pues eso: alta de cliente")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Cliente creado con éxito", 
					 content = @Content(schema = @Schema(implementation = RespuestaOk.class))),
		@ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content)
	})
	public ResponseEntity<Respuesta> insertar(@Valid @RequestBody ClienteDto clienteDto) throws ClienteException {
		Cliente cliente = clienteDto.asCliente();
		gestorClientes.insertar(cliente);
		
		Data data = new Data("Cliente insertado", new ClienteDto(cliente));
		RespuestaOk r = new RespuestaOk("201","SUCCESS", data);
		return new ResponseEntity<>(r, HttpStatus.CREATED);		
	}
	
	@PutMapping(path = "/{id}", consumes = { "application/json", "application/xml" })
	@Operation(summary = "Modificar un cliente", description = "Actualiza los datos de un cliente existente mediante su ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Cliente actualizado con éxito"),
		@ApiResponse(responseCode = "404", description = "Cliente no encontrado")
	})
	public ResponseEntity<Respuesta> modificar(
			@Parameter(description = "ID del cliente a modificar") @PathVariable Integer id, 
			@Valid @RequestBody ClienteDto clienteDto) throws ClienteException {
		
		Cliente cliente = clienteDto.asCliente();
		cliente.setId(id);
		
		gestorClientes.modificar(cliente);
		
		Data data = new Data("Cliente modificado", new ClienteDto(cliente));
		RespuestaOk r = new RespuestaOk("200","SUCCESS", data);
		return new ResponseEntity<>(r, HttpStatus.OK);		
	}	
	
	@DeleteMapping(path = "/{id}")
	@Operation(summary = "Baja de cliente", description = "Elimina de forma permanente un cliente por su ID")
	public void borrar(@Parameter(description = "ID del cliente a eliminar") @PathVariable Integer id) {
		// Nota: El código actual contiene un error intencional (división por cero)
		int a = 10 / 0;
		System.out.println(a);
	}
	
	@GetMapping(path = "/{id}")
	@Operation(summary = "Buscar un cliente por ID", description = "Devuelve los detalles de un único cliente")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Cliente encontrado"),
		@ApiResponse(responseCode = "404", description = "Cliente no existe", 
					 content = @Content(schema = @Schema(implementation = RespuestaError.class)))
	})
	public ResponseEntity<Respuesta> buscar(@PathVariable Integer id) {
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
	@Operation(summary = "Listar todos los clientes", description = "Obtiene una lista completa de los clientes registrados")
	public ResponseEntity<Respuesta> listar() {		
		List<ClienteDto> clientesDto = gestorClientes.listar()
				.stream()
				.map(c -> new ClienteDto(c))
				.collect(Collectors.toList());
		
		Data data = new Data("Listado de clientes", clientesDto);
		RespuestaOk r = new RespuestaOk("200","SUCCESS", data);
		return new ResponseEntity<>(r, HttpStatus.OK);			
	}
}
