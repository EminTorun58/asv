package com.numero.uno.wedify.wedify.features.venueroom;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VenueRoomRepository extends CrudRepository<VenueRoom, Long> {

	@Override
	List<VenueRoom> findAll();

}
