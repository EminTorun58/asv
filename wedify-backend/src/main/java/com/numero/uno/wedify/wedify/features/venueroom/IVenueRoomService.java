package com.numero.uno.wedify.wedify.features.venueroom;


import javax.validation.Valid;
import java.util.List;

public interface IVenueRoomService {

	List<VenueRoom> getRoomsByVenueId(Long venueId);

	VenueRoom saveVenueRoom(@Valid VenueRoom venueRoom, Long venueId);

	void deleteVenueRoom(Long id);

	VenueRoom getVenueRoomById(Long id);

	VenueRoom updateVenueRoom(VenueRoom venueRoom, Long id);

}
