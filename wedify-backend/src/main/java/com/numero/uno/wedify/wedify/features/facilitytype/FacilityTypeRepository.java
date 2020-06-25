package com.numero.uno.wedify.wedify.features.facilitytype;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FacilityTypeRepository extends CrudRepository<FacilityType, Long> {

	@Override
	List<FacilityType> findAll();

	Optional<FacilityType> findByType(FacilityTypeEnum type);

}
