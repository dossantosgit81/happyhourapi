package br.com.happyhour.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.happyhour.domain.model.User;
import br.com.happyhour.domain.repositories.UserRepository;
import br.com.happyhour.exceptions.EmailAlredyExists;
import br.com.happyhour.exceptions.PasswordInvalidException;
import br.com.happyhour.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository repository;

	@Override
	@Transactional
	public User save(User user) {
		Optional<User> userFound = repository.findOptionalByEmail(user.getEmail());
				
		if(userFound.isPresent()) {
			throw new EmailAlredyExists("Este email já existe na base de dados");
		}
		
		String passwordCrypto = encoder.encode(user.getPassword());
		user.setPassword(passwordCrypto);
		
		return repository.save(user);
	}
	
	@Override
	public UserDetails auth(br.com.happyhour.domain.model.User user) {
		UserDetails userDetails = loadUserByUsername(user.getEmail());
		boolean passwordsTrue = encoder.matches(user.getPassword(), userDetails.getPassword());
		
		if(passwordsTrue) {
			return userDetails;
		}
		
		throw new PasswordInvalidException("Senha invalida");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.findOptionalByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado na base de dados"));
		
		System.out.println(user.isAdmin());
		
		String[] roles = user.isAdmin() ? new String[]{"ADMIN", "USER"}
		: new String[] {"USER"};
		
		return org.springframework.security.core.userdetails.
				User.builder()
				.username(user.getEmail())
				.password(user.getPassword())
				.roles(roles)
				.build();
	}

}
