package br.com.happyhour.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.happyhour.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findOptionalByEmail(String email);
}
