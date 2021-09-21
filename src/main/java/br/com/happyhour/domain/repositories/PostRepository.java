package br.com.happyhour.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.happyhour.domain.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
