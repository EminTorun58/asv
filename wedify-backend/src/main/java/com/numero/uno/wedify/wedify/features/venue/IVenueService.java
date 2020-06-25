package com.numero.uno.wedify.wedify.features.venue;

import javax.validation.Valid;
import java.util.List;

public interface IVenueService {

    Venue saveVenue(@Valid Venue venue, Long companyId);

    Venue getVenueById(Long id);

    List<Venue> getAllVenues();

    Venue updateVenue(Venue venue, Long venueId);

    List<Venue> getAllVenuesByCompany(Long companyId);

}
