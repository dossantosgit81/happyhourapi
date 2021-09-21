package br.com.happyhour.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.happyhour.domain.model.User;
import br.com.happyhour.domain.repositories.UserRepository;
import br.com.happyhour.exceptions.PasswordInvalidException;
import br.com.happyhour.jwt.JWTService;
import br.com.happyhour.rest.dto.CredentialsDTO;
import br.com.happyhour.rest.dto.TokenDTO;
import br.com.happyhour.rest.dto.UserDTO;
import br.com.happyhour.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<UserDTO> list(){
		List<UserDTO> result = userRepository.findAll().stream()
				.map(entity -> modelMapper.map(entity, UserDTO.class)).collect(Collectors.toList());
		return result;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public UserDTO create(@Valid @RequestBody User user) {
			User savedUser = userService.save(user);
			return modelMapper.map(savedUser, UserDTO.class);
	}
	
	@PostMapping("/auth")
	@ResponseStatus(HttpStatus.OK)
	public TokenDTO auth(@RequestBody CredentialsDTO credentials, HttpServletResponse res, HttpServletRequest req) {
		try {
			User user = new User();
			user.setEmail(credentials.getEmail());
			user.setPassword(credentials.getPassword());
			UserDetails userDetails = userService.auth(user);
			String token = "";
			if(userDetails != null) {	
				 token = jwtService.generateToken(user.getEmail());
				 res.addHeader("Authorization","Bearer "+ token);
				 res.addHeader("access-control-expose-headers", "Authorization");
			}
			
					
			return new TokenDTO(user.getEmail(), token);
		}catch(PasswordInvalidException | UsernameNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}

}
