package br.com.happyhour.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.happyhour.exceptions.EmailAlredyExists;
import br.com.happyhour.exceptions.InvalidTokenException;
import br.com.happyhour.rest.exceptions.model.ApiErrors;

@RestControllerAdvice
public class AppControllerAdvice {
	
	@ExceptionHandler(EmailAlredyExists.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleEmailAlredyExists(EmailAlredyExists e) {
		String message = e.getMessage();
		
		return new ApiErrors(message, HttpStatus.BAD_REQUEST.toString(), e.getCause().toString());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		BindingResult bidingResult = ex.getBindingResult();
		
		return new ApiErrors(bidingResult, HttpStatus.BAD_REQUEST.toString(), ex.getCause().toString());
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleMethodUserNotFound(UsernameNotFoundException userNotFoundException) {
		
		return new ApiErrors(userNotFoundException.getMessage(), HttpStatus.BAD_REQUEST.toString(), userNotFoundException.getCause().toString());
	}
	
	@ExceptionHandler(InvalidTokenException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleMethodUserNotFound(InvalidTokenException invalidTokenException) {
		
		return new ApiErrors(invalidTokenException.getMessage(), HttpStatus.BAD_REQUEST.toString(), invalidTokenException.getCause().toString());
	}

}
