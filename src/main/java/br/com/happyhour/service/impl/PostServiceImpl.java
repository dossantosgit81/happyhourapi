package br.com.happyhour.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.happyhour.domain.model.Post;
import br.com.happyhour.domain.model.User;
import br.com.happyhour.domain.repositories.PostRepository;
import br.com.happyhour.domain.repositories.UserRepository;
import br.com.happyhour.service.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Post save(Post post) {
		Optional<User> user = userRepository.findOptionalByEmail(post.getUser().getEmail());
		if(user.isPresent()) {
			Post savedPost = postRepository.save(post);
			
			return savedPost;
		}
		
		throw new UsernameNotFoundException("Usuario não encontrado");
	}

}
