package com.numero.uno.wedify.wedify.features.venueroom;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VenueRoomController {

	private final IVenueRoomService venueRoomService;

	public VenueRoomController(IVenueRoomService venueRoomService) {
		this.venueRoomService = venueRoomService;
	}

	@GetMapping(path = "/facilities/venues/rooms/{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("isAuthenticated()")
	public VenueRoom getVenueRoom(@PathVariable Long id) {
		return venueRoomService.getVenueRoomById(id);
	}

	@PostMapping(path = "/facilities/venues/{venueId}/rooms", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("isAuthenticated()")
	public VenueRoom registerVenue(@Validated({VenueRoom.Create.class}) VenueRoom venueRoom, @PathVariable Long venueId) {

		return venueRoomService.saveVenueRoom(venueRoom, venueId);
	}

	@GetMapping(path = "/facilities/venues/{venueId}/rooms")
	@ResponseStatus(HttpStatus.OK)
	public List<VenueRoom> getAllRoomByVenueId(@PathVariable Long venueId) {

		return venueRoomService.getRoomsByVenueId(venueId);
	}

	@DeleteMapping(path = "/facilities/venues/rooms/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("isAuthenticated()")
	public void deleteVenueRoom(@PathVariable Long id) {

		venueRoomService.deleteVenueRoom(id);
	}

	@PutMapping(path = "/facilities/venues/rooms/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("isAuthenticated()")
	public VenueRoom updateVenueRoom(@Validated({VenueRoom.Update.class}) @RequestBody VenueRoom venueRoom, @PathVariable Long id) {
		return venueRoomService.updateVenueRoom(venueRoom, id);
	}

}
