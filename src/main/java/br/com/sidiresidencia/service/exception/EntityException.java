package br.com.sidiresidencia.service.exception;

public class EntityException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	
	public EntityException(String msg) {
		super(msg);
	}
}
