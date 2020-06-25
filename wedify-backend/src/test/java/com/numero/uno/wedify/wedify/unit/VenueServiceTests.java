package com.numero.uno.wedify.wedify.unit;

import com.numero.uno.wedify.wedify.exception.ResourceAlreadyExistsException;
import com.numero.uno.wedify.wedify.exception.ResourceNotFoundException;
import com.numero.uno.wedify.wedify.features.company.Company;
import com.numero.uno.wedify.wedify.features.facilitytype.FacilityType;
import com.numero.uno.wedify.wedify.features.facilitytype.FacilityTypeEnum;
import com.numero.uno.wedify.wedify.features.venueroom.VenueRoom;
import com.numero.uno.wedify.wedify.features.facility.IFacilityService;
import com.numero.uno.wedify.wedify.features.facilitytype.IFacilityTypeService;
import com.numero.uno.wedify.wedify.features.venue.Venue;
import com.numero.uno.wedify.wedify.features.venue.VenueRepository;
import com.numero.uno.wedify.wedify.features.venue.VenueServiceImpl;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VenueServiceTests {

	@Mock
	private VenueRepository venueRepository;

	@Mock
	private IFacilityService serviceService;

	@InjectMocks
	private VenueServiceImpl venueService;

	@Test
	void testGetAllVenues_returnsTestData() {
		Mockito.when(venueRepository.findAll()).thenReturn(Collections.singletonList(getTestData()));
		Assertions.assertEquals(Collections.singletonList(getTestData()), venueService.getAllVenues());
	}

	@Test
	void testGetVenueById_returnsVenue() {
		Mockito.when(venueRepository.findById(1L)).thenReturn(Optional.of(getTestData()));

		MatcherAssert.assertThat(venueService.getVenueById(1L), Matchers.samePropertyValuesAs(getTestData()));
	}

	@Test
	void testGetNonExistentVenueById_returnsResourceNotFoundException() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> venueService.getVenueById(1L));
	}

	//Not working due to security helper.
//	@Test
//	void testSaveVenue_returnsVenue() {
//		Mockito.when(venueRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
//		Mockito.when(serviceTypeService.getVenueFacilityType()).thenReturn(new FacilityType(1L, "Venue"));
//
//		Venue savedVenue = venueService.saveVenue(getTestData());
//
//		MatcherAssert.assertThat(savedVenue, Matchers.samePropertyValuesAs(getTestData()));
//	}

	//Not working due to security helper.
//	@Test
//	void testSaveVenueAlreadyExists_returnsResourceAlreadyExistsException() {
//		Mockito.doThrow(ResourceAlreadyExistsException.class).when(serviceService).validateFacilityDoesntExist(any());
//		Assertions.assertThrows(ResourceAlreadyExistsException.class, () -> venueService.saveVenue(getTestData(), 1L));
//	}

	private Venue getTestData() {

		FacilityType facilityType = new FacilityType(1L, FacilityTypeEnum.VENUE);
		Company company = new Company();
		company.setId(1L);

		List<VenueRoom> rooms = new ArrayList<>();
		VenueRoom room = new VenueRoom();
		room.setId(1L);

		return new Venue(1L, "Buiksloterkerk", "A very nice church for a wedding party.", "Contact us for a quotation,",
				10, 60, facilityType, "",company, "Churchstreet", "20", "1900 TP", "Amsterdam", rooms);
	}

}
