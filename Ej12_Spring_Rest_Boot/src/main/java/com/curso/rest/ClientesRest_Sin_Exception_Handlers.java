package com.curso.rest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.ServicioClientes;
import com.curso.modelo.negocio.excepcion.ClienteException;
import com.curso.rest.dto.ClienteDto;
import com.curso.rest.dto.Data;
import com.curso.rest.dto.Respuesta;
import com.curso.rest.dto.RespuestaError;
import com.curso.rest.dto.RespuestaOk;
import com.curso.rest.dto.Zasca;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

/*
GET    /clientes
GET    /clientes/id
POST   /clientes
PUT    /clientes/id
DELETE /clientes/id
*/


//@Controller
//@RestController
@RequestMapping(
		path = "/clientes",
		produces = { "application/json", "application/xml" }
	)
public class ClientesRest_Sin_Exception_Handlers {
	
	private ServicioClientes gestorClientes;
	
	public ClientesRest_Sin_Exception_Handlers(ServicioClientes gestorClientes) {
		super();
		this.gestorClientes = gestorClientes;
	}
	
	/*
	@RequestMapping(
			method = RequestMethod.POST,
			path = "/clientes"
		)
	*/

	/*
	POST /cientes
	Content-type : application/json
	Accept: application/json
	-------------------------------
	{ cliente }	
	
	201 CREATED
	CT: app/json
	-------------------------------
	{ clienteInsertado }
	
	400 BAD REQUEST
	
	500 INTERNAL SERVER ERROR
	
	*/	
	@PostMapping(
			consumes = { "application/json", "application/xml" }
			//produces = { "application/json", "application/xml" }
		)
	//@ResponseBody
	public ResponseEntity<Respuesta> insertar(@Valid @RequestBody ClienteDto clienteDto, BindingResult result) {
		
		//Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
		//Set<ConstraintViolation<ClienteDto>> errores = validador.validate(clienteDto);
		//errores.stream().forEach( cv -> System.out.println(cv.getPropertyPath()+", "+cv.getMessage() ));
		
		if(result.hasErrors()) {
			Zasca error = new Zasca("400", "Datos inválidos");
			RespuestaError r = new RespuestaError("400","ERROR", error);
			return new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);				
		}		
		
		Cliente cliente = clienteDto.asCliente();
		try {
			gestorClientes.insertar(cliente);
		} catch (ClienteException e) {
			e.printStackTrace();
			Zasca error = new Zasca("400", e.getMessage());
			RespuestaError r = new RespuestaError("400","ERROR", error);
			return new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);	
		} catch (Exception e) {
			e.printStackTrace();			
			Zasca error = new Zasca("500", e.getMessage());
			RespuestaError r = new RespuestaError("500","ERROR", error);
			return new ResponseEntity<>(r, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		Data data = new Data("Cliente insertado", new ClienteDto(cliente));
		RespuestaOk r = new RespuestaOk("201","SUCCESS", data);
		return new ResponseEntity<>(r, HttpStatus.CREATED);		
		
	}
	
	/*
	PUT /cientes/{id}
	Content-type : application/json
	-----------------
	{ cliente }
	
	public void modificar(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, ServletContext svCtx) {
    */
	@PutMapping( 
			path = "/{id}",
			consumes = { "application/json", "application/xml" }
		)
	public ResponseEntity<Respuesta> modificar(@PathVariable Integer id, @Valid @RequestBody ClienteDto clienteDto, BindingResult result) {
		
		if(result.hasErrors()) {
			Zasca error = new Zasca("400", "Datos inválidos");
			RespuestaError r = new RespuestaError("400","ERROR", error);
			return new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);				
		}			
		
		Cliente cliente = clienteDto.asCliente();
		cliente.setId(id);
		
		try {
			gestorClientes.modificar(cliente);
		} catch (ClienteException e) {
			e.printStackTrace();
			Zasca error = new Zasca("400", e.getMessage());
			RespuestaError r = new RespuestaError("400","ERROR", error);
			return new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);			
			
		} catch (Exception e) {
			e.printStackTrace();			
			Zasca error = new Zasca("500", e.getMessage());
			RespuestaError r = new RespuestaError("500","ERROR", error);
			return new ResponseEntity<>(r, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		Data data = new Data("Cliente insertado", new ClienteDto(cliente));
		RespuestaOk r = new RespuestaOk("200","SUCCESS", data);
		return new ResponseEntity<>(r, HttpStatus.OK);		
	}	
	
	@DeleteMapping( path = "/{id}")
	public void borrar(@PathVariable Integer id) {
		int a = 10 / 0;
	}
	
	@GetMapping( path = "/{id}")
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
