package com.numero.uno.wedify.wedify.features.venue;

import com.numero.uno.wedify.wedify.exception.ResourceNotFoundException;
import com.numero.uno.wedify.wedify.features.company.Company;
import com.numero.uno.wedify.wedify.features.company.ICompanyService;
import com.numero.uno.wedify.wedify.features.facility.IFacilityService;
import com.numero.uno.wedify.wedify.features.facilitytype.FacilityTypeEnum;
import com.numero.uno.wedify.wedify.features.facilitytype.IFacilityTypeService;
import com.numero.uno.wedify.wedify.features.venueroom.VenueRoom;
import com.numero.uno.wedify.wedify.util.SecurityHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class VenueServiceImpl implements IVenueService {

    private final VenueRepository venueRepository;

    private final IFacilityService facilityService;

    private final IFacilityTypeService facilityTypeService;

    private final ICompanyService companyService;

    public VenueServiceImpl(VenueRepository venueRepository, IFacilityService facilityService, IFacilityTypeService facilityTypeService, ICompanyService companyService) {
        this.venueRepository = venueRepository;
        this.facilityService = facilityService;
        this.facilityTypeService = facilityTypeService;
        this.companyService = companyService;
    }

    @Override
    @Validated({Venue.Create.class, VenueRoom.NullId.class})
    @PreAuthorize("isAuthenticated()")
    @Transactional
    public Venue saveVenue(@Valid Venue venue, Long companyId) {

        // Check if the authenticated user is the company that the venue is added to.
        SecurityHelper.validatePrincipalIsCompany(companyId);

        // Check if the facility already exists.
        facilityService.validateFacilityDoesntExist(venue.getName());

        // Set the facility type to Venue and add the company to the venue.
        venue.setFacilityType(facilityTypeService.getFacilityTypeByType(FacilityTypeEnum.VENUE));
        venue.setCompany(companyService.getCompanyById(companyId));
        venue.setRooms(new ArrayList<>());

        // Save and return the Venue.
        return venueRepository.save(venue);
    }

    @Override
    public Venue getVenueById(Long id) {
        // Get the venue from the repository and validate if it exists.
        Optional<Venue> venue = venueRepository.findById(id);

        return venue.orElseGet(() -> venue.orElseThrow(() -> new ResourceNotFoundException(Venue.class.getSimpleName(), id)));
    }

    @Override
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @Validated({Venue.Update.class})
    public Venue updateVenue(Venue venue, Long venueId) {

        // Check if the venue exists.
        Venue venueToUpdate = getVenueById(venueId);

        // Check if the authenticated user is the owner of the venue.
        SecurityHelper.validatePrincipalIsCompany(venueToUpdate.getCompany().getId());

        // If the venue name has changed check if it doesn't exist already.
        if (!venueToUpdate.getName().equals(venue.getName()))
            facilityService.validateFacilityDoesntExist(venue.getName());

        // Keep the company as a venue owner and keep the facility type.
        venue.setId(venueToUpdate.getId());
        venue.setCompany(venueToUpdate.getCompany());
        venue.setFacilityType(venueToUpdate.getFacilityType());
        venue.setRooms(venueToUpdate.getRooms());

        return venueRepository.save(venue);
    }

    public List<Venue> getAllVenuesByCompany(Long companyId) {
        Company company = companyService.getCompanyById(companyId);

        return venueRepository.findByCompany(company);

    }
}
