package br.com.happyhour.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.happyhour.domain.repositories.PostRepository;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public void save(HttpServletRequest request, HttpServletResponse response) {
		
		String s1 =request.getHeader("Authorization");
		System.out.println("S11111111"+ s1);
		
	
	}
	
}
