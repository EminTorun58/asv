package com.numero.uno.wedify.wedify.features.usertype;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserTypeRepository extends CrudRepository<UserType, Long> {

	@Override
	List<UserType> findAll();

	Optional<UserType> findByType(UserTypeEnum type);

}
