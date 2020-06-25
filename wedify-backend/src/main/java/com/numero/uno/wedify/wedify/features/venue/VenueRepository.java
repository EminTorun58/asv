package com.numero.uno.wedify.wedify.features.venue;

import com.numero.uno.wedify.wedify.features.company.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VenueRepository extends CrudRepository<Venue, Long> {

	@Override
	List<Venue> findAll();

	List<Venue> findByCompany(Company company);

}
