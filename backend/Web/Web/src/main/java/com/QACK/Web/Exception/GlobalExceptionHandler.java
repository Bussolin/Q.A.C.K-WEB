package com.QACK.Web.Exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler  {
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<StandartError> httpRequestMethodNotSupportedException( HttpRequestMethodNotSupportedException e, HttpServletRequest request ){
		String error = "Not allowed";
		HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
		StandartError err = new StandartError( Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI() );
		return ResponseEntity.status( status ).body( err );
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandartError> resourceNotFoundException( ResourceNotFoundException e, HttpServletRequest request ){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandartError err = new StandartError( Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI() );
		return ResponseEntity.status( status ).body( err );
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandartError> illegalArgumentException( IllegalArgumentException e, HttpServletRequest request ){
		String error = "Bad Request, review you RequestBody";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandartError err = new StandartError( Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI() );
		return ResponseEntity.status( status ).body( err );
	}
}
