package com.numero.uno.wedify.wedify.features.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

	@Override
	List<User> findAll();

	Optional<User> findByEmail(String email);

}
