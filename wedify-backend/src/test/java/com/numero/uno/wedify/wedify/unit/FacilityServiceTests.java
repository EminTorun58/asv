package com.numero.uno.wedify.wedify.unit;

import com.numero.uno.wedify.wedify.exception.ResourceAlreadyExistsException;
import com.numero.uno.wedify.wedify.exception.ResourceNotFoundException;
import com.numero.uno.wedify.wedify.features.company.Company;
import com.numero.uno.wedify.wedify.features.facility.Facility;
import com.numero.uno.wedify.wedify.features.facility.FacilityRepository;
import com.numero.uno.wedify.wedify.features.facility.FacilityServiceImpl;
import com.numero.uno.wedify.wedify.features.facilitytype.FacilityType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FacilityServiceTests {

	@Mock
	private FacilityRepository facilityRepository;

	@InjectMocks
	private FacilityServiceImpl serviceService;

	@Test
	void testGetAllServices_returnsTestData() {
		Mockito.when(facilityRepository.findAll()).thenReturn(Collections.singletonList(getTestData()));
		Assertions.assertEquals(Collections.singletonList(getTestData()), serviceService.getAllFacilities());
	}

	@Test
	void testGetServiceId_returnsService() {
		Mockito.when(facilityRepository.findById(1L)).thenReturn(Optional.of(getTestData()));
		Assertions.assertEquals(getTestData(), serviceService.getFacilityById(1L));
	}

	@Test
	void testGetServiceById_throwsResourceNotFoundException() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> serviceService.getFacilityById(1L));
	}

	// Not working because of SecurityContext not having an authenticated UserPrincipal.
//	@Test
//	void testDeleteService_invokedServiceRepositoryDelete() {
//		Facility serviceToDelete = getTestData();
//		Mockito.when(facilityRepository.findById(serviceToDelete.getId())).thenReturn(Optional.of(getTestData()));
//
//		serviceService.deleteFacility(serviceToDelete.getId());
//		Mockito.verify(facilityRepository).delete(getTestData());
//	}

	@Test
	void testDeleteNonExistingCompany_throwsResourceNotFoundException() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> serviceService.deleteFacility(1L));
	}

	@Test
	void testValidateUserDoesntExist_throwsUserNotFoundException() {
		Mockito.when(facilityRepository.findByName("foodcompany")).thenReturn(Optional.of(getTestData()));

		Assertions.assertThrows(ResourceAlreadyExistsException.class, () -> serviceService.validateFacilityDoesntExist("foodcompany"));
	}

	@Test
	void testValidateUserDoesntExist_throwsNothing() {
		Assertions.assertDoesNotThrow(() -> serviceService.validateFacilityDoesntExist("foodcompany"));
	}

	private Facility getTestData() {
		Company company = new Company();
		company.setId(1L);
		return new Facility(1L, "Bootverhuur", "Nice bootverhuur", "Offerte op aanvraag.",
				10, 20, new FacilityType(), "", company);
	}

}
