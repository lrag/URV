package com.curso.rest;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curso.modelo.negocio.excepcion.NegocioException;
import com.curso.rest.dto.Mensaje;
import com.curso.rest.dto.Respuesta;
import com.curso.rest.dto.RespuestaError;
import com.curso.rest.dto.Zasca;

//@ControllerAdvice
//@ControllerAdvice( basePackages = "com.curso.rest")
public class ControladorExcepciones {

	public ControladorExcepciones() {
		super();
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
	
	@ExceptionHandler(NegocioException.class)
	@ResponseBody
	public ResponseEntity<?> handleNegocioException(NegocioException e) {
		
		System.out.println("=NEGOCIO EXCEPCION CAPTURADA EN CONTROLADOR EXCEPCIONES==============================");
		System.out.println(e.getMessage());
		//e.printStackTrace();
		
		
		Zasca error = new Zasca("400", "Error de negocio", e.getMessage());
		RespuestaError r = new RespuestaError("400","ERROR", error);
		return new ResponseEntity<RespuestaError>(r, HttpStatus.BAD_REQUEST);		
	}	
	
	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public ResponseEntity<?> handleException(Throwable e) {
		System.out.println("=THOWABLE CAPTURADO EN CONTROLADOR EXCEPCIONES==============================");
		System.out.println(e.getMessage());
		//e.printStackTrace();
		
		Zasca error = new Zasca("500", e.getMessage());
		RespuestaError r = new RespuestaError("500","ERROR", error);
		return new ResponseEntity<>(r, HttpStatus.INTERNAL_SERVER_ERROR);				
	}
	
}
