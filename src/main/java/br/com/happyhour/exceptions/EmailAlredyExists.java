package br.com.happyhour.exceptions;

public class EmailAlredyExists extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public EmailAlredyExists(String message) {
		super(message);
	}

	
	
}
