package br.com.happyhour.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.happyhour.domain.model.User;

public interface UserService extends UserDetailsService{
	
	User save(User user);

}
