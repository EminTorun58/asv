package com.numero.uno.wedify.wedify.features.company;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Long> {

	@Override
	List<Company> findAll();

	Optional<Company> findByKvk(String kvk);

	List<Company> findAllByCity(String city);

}
