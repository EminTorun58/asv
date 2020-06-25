package com.numero.uno.wedify.wedify.features.venue;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class VenueController {

	private final IVenueService venueService;


	@Autowired
	public VenueController(IVenueService venueService) {
		this.venueService = venueService;
	}

	@PostMapping(path = "/companies/{companyId}/facilities/venues", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("isAuthenticated()")
	public Venue registerVenue(@Validated({Venue.Create.class}) @RequestBody Venue venue, @PathVariable Long companyId) {

		return venueService.saveVenue(venue, companyId);
	}

	@GetMapping(path = "/facilities/venues")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Venue> getAllVenues() {

		return venueService.getAllVenues();
	}

	@GetMapping(path = "/facilities/venues/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Venue getVenueById(@PathVariable Long id) {

		return venueService.getVenueById(id);
	}


	@PutMapping(path = "/facilities/venues/{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("isAuthenticated()")
	public Venue updateVenue(@Validated({Venue.Update.class}) @RequestBody Venue venue, @PathVariable Long id) {

		return venueService.updateVenue(venue, id);
	}

	@GetMapping(path = "/companies/{companyId}/facilities/venues")
	@ResponseStatus(HttpStatus.OK)
	public List<Venue> getVenuesByCompany(@PathVariable Long companyId) {

		return venueService.getAllVenuesByCompany(companyId);

	}

}
