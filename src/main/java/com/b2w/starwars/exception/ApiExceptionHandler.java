package com.b2w.starwars.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = {ObjetoNaoEncontradoException.class})
	public ResponseEntity<Object> handleObjetoNaoEncontradoException(ObjetoNaoEncontradoException e){
		HttpStatus notFound = HttpStatus.NOT_FOUND;
		ApiException apiException = new ApiException(
				e.getMessage(),
                e, 
				notFound, 
				ZonedDateTime.now(ZoneId.of("Z"))
		);
		return new ResponseEntity<>(apiException, notFound);
	}
	
	@ExceptionHandler(value = {ObjetoJaCadastrado.class})
	public ResponseEntity<Object> handleObjetoNaoEncontradoException(ObjetoJaCadastrado e){
		HttpStatus notFound = HttpStatus.ALREADY_REPORTED;
		ApiException apiException = new ApiException(
				e.getMessage(), 
				e, 
				notFound, 
				ZonedDateTime.now(ZoneId.of("Z"))
		);
		return new ResponseEntity<>(apiException, notFound);
	}

}
