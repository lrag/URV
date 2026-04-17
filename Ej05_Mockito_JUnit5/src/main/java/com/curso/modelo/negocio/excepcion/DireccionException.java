package com.curso.modelo.negocio.excepcion;

public class DireccionException extends Exception {

	public DireccionException() {
	}

	public DireccionException(String message) {
		super(message);
	}

	public DireccionException(Throwable cause) {
		super(cause);
	}

	public DireccionException(String message, Throwable cause) {
		super(message, cause);
	}

	public DireccionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
