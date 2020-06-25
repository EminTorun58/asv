package com.numero.uno.wedify.wedify.features.facility;

import com.numero.uno.wedify.wedify.features.company.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FacilityRepository extends CrudRepository<Facility, Long> {

	@Override
	List<Facility> findAll();

	List<Facility> findByCompany(Company company);

	Optional<Facility> findByName(String name);
}
