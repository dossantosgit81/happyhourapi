package br.com.happyhour.rest.resources;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.happyhour.domain.model.User;
import br.com.happyhour.rest.dto.UserDTO;
import br.com.happyhour.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public UserDTO create(@Valid @RequestBody User user) {
			User savedUser = userService.save(user);
			return modelMapper.map(savedUser, UserDTO.class);
	}

}
