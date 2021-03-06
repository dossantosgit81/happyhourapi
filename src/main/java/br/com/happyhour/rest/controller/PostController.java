package br.com.happyhour.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.happyhour.domain.model.Post;
import br.com.happyhour.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	
	@Autowired
	private PostService postService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Post save(@RequestBody Post post, HttpServletRequest request) {
		return postService.save(post);
		
	}
	
	
}
