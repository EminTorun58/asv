package com.numero.uno.wedify.wedify.features.venueroom;

import com.numero.uno.wedify.wedify.exception.ResourceNotFoundException;
import com.numero.uno.wedify.wedify.features.venue.IVenueService;
import com.numero.uno.wedify.wedify.features.venue.Venue;
import com.numero.uno.wedify.wedify.util.SecurityHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class VenueRoomServiceImpl implements IVenueRoomService {

    private final VenueRoomRepository venueRoomRepository;

    private final IVenueService venueService;

    public VenueRoomServiceImpl(VenueRoomRepository venueRoomRepository, IVenueService venueService) {
        this.venueRoomRepository = venueRoomRepository;
        this.venueService = venueService;
    }

    @Override
    public VenueRoom getVenueRoomById(Long id) {
        Optional<VenueRoom> venueRoom = venueRoomRepository.findById(id);

        return venueRoom.orElseGet(() -> venueRoom.orElseThrow(() -> new ResourceNotFoundException(VenueRoom.class.getSimpleName(), id)));
    }

    @Override
    public List<VenueRoom> getRoomsByVenueId(Long venueId) {
        Venue venue = venueService.getVenueById(venueId);

        return venue.getRooms();
    }

    @Override
    @Validated({VenueRoom.Create.class})
    @PreAuthorize("isAuthenticated()")
    public VenueRoom saveVenueRoom(@Valid VenueRoom venueRoom, Long venueId) {

        // Get the venue
        Venue venue = venueService.getVenueById(venueId);

        // Validate the principal is the owner of the venue.
        SecurityHelper.validatePrincipalIsCompany(venue.getCompany().getId());

        // Add the relation between the venue venue room and the venue.
        venue.getRooms().add(venueRoom);

        // Persist the venue venue room and return it.
        return venueRoomRepository.save(venueRoom);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public void deleteVenueRoom(Long id) {
        Optional<VenueRoom> venueRoom = venueRoomRepository.findById(id);
        if (venueRoom.isPresent()) {
            SecurityHelper.validatePrincipalIsCompany(venueRoom.get().getVenue().getCompany().getId());
            venueRoom.get().getVenue().getRooms().remove(venueRoom.get());
            venueRoomRepository.deleteById(id);
        }

    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public VenueRoom updateVenueRoom(VenueRoom venueRoom, Long venueRoomId) {

        // Get the venue room that needs to be updated.
        VenueRoom venueRoomToUpdate = getVenueRoomById(venueRoomId);

        // Validate if the user is authorized to update the venue room.
        SecurityHelper.validatePrincipalIsCompany(venueRoomToUpdate.getVenue().getCompany().getId());

        // Set the id and venue to the previous venue room.
        venueRoom.setId(venueRoomToUpdate.getId());
        venueRoom.setVenue(venueRoomToUpdate.getVenue());

        // save and return the updated venue room.
        return venueRoomRepository.save(venueRoom);
    }
}
