package br.com.sidiresidencia.controller.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.sidiresidencia.service.exception.DatabaseException;
import br.com.sidiresidencia.service.exception.EntityException;
import br.com.sidiresidencia.service.exception.ProfileException;
import br.com.sidiresidencia.service.exception.ReservationException;
import br.com.sidiresidencia.service.exception.StationException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError error = new ValidationError();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Validation exception");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());

		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			error.addError(f.getField(), f.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(error);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StandardError> violation(ConstraintViolationException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; 
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}    
	
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<StandardError> InvalidFormat(InvalidFormatException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; 
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not found");
		err.setMessage("Informed date does not exist");
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(EntityException.class)
	public ResponseEntity<StandardError> entityNotFound(EntityException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(ReservationException.class)
	public ResponseEntity<StandardError> Reservation(ReservationException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Reservation exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(StationException.class)
	public ResponseEntity<StandardError> Station(StationException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; 
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Reservation exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ProfileException.class)
	public ResponseEntity<StandardError> Profile(ProfileException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; 
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Reservation exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
