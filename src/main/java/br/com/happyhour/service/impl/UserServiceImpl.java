package br.com.happyhour.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.happyhour.domain.model.User;
import br.com.happyhour.domain.repositories.UserRepository;
import br.com.happyhour.exceptions.EmailAlredyExists;
import br.com.happyhour.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
//	@Autowired
//	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository repository;

	@Override
	public User save(User user) {
		Optional<User> userFound = repository.findOptionalByEmail(user.getEmail());
				
		if(userFound.isPresent()) {
			throw new EmailAlredyExists("Este email já existe na base de dados");
		}
		
//		String passwordCrypto = encoder.encode(user.getPassword());
//		user.setPassword(passwordCrypto);
		
		return repository.save(user);
	}

}
