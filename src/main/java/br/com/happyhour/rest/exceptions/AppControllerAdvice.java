package br.com.happyhour.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.happyhour.exceptions.EmailAlredyExists;
import br.com.happyhour.rest.exceptions.model.ApiErrors;

@RestControllerAdvice
public class AppControllerAdvice {
	
	@ExceptionHandler(EmailAlredyExists.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleEmailAlredyExists(EmailAlredyExists e) {
		String error = e.getMessage();
		
		return new ApiErrors(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		BindingResult bidingResult = ex.getBindingResult();
		
		return new ApiErrors(bidingResult);
	}

}
