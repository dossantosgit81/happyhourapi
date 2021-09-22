package br.com.happyhour.rest.exceptions.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.validation.BindingResult;

public class ApiErrors {
	
	private List<String> messagesUser;
	private String messageDeveloper;
	private String statusCode;
	
	public ApiErrors(BindingResult bindingResult, String status, String cause) {
		this.messagesUser = new ArrayList<>();		
		this.statusCode = status;
		this.messageDeveloper = cause;
		
		bindingResult.getAllErrors().forEach(message -> this.messagesUser.add(message.getDefaultMessage()));
	}
	
	public ApiErrors(String message, String status, String cause) {
		this.messagesUser = Arrays.asList(message);
		this.statusCode = status;
		this.messageDeveloper = cause;
	}

	public List<String> getMessages() {
		return messagesUser;
	}

	public void setMessages(List<String> messages) {
		this.messagesUser = messages;
	}

	public List<String> getMessagesUser() {
		return messagesUser;
	}

	public void setMessagesUser(List<String> messagesUser) {
		this.messagesUser = messagesUser;
	}

	public String getMessageDeveloper() {
		return messageDeveloper;
	}

	public void setMessageDeveloper(String messageDeveloper) {
		this.messageDeveloper = messageDeveloper;
	}

	public String getStatus() {
		return statusCode;
	}

	public void setStatus(String status) {
		this.statusCode = status;
	}

}
