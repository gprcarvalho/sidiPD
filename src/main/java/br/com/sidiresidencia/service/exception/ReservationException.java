package br.com.sidiresidencia.service.exception;

public class ReservationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ReservationException(String msg) {
		super(msg);
	}

}
