package com.numero.uno.wedify.wedify.unit;

import com.numero.uno.wedify.wedify.exception.ResourceNotFoundException;
import com.numero.uno.wedify.wedify.features.facilitytype.FacilityType;
import com.numero.uno.wedify.wedify.features.facilitytype.FacilityTypeEnum;
import com.numero.uno.wedify.wedify.features.facilitytype.FacilityTypeRepository;
import com.numero.uno.wedify.wedify.features.facilitytype.FacilityTypeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FacilityTypeServiceTests {

	@Mock
	private FacilityTypeRepository facilityTypeRepository;

	@InjectMocks
	private FacilityTypeServiceImpl serviceTypeService;


	@Test
	void testGetAllUserTypes_returnsTestData() {
		Mockito.when(facilityTypeRepository.findAll()).thenReturn(getTestData());

		Assertions.assertEquals(getTestData(), serviceTypeService.getAllFacilityTypes());
	}

	@Test
	void testGetUserTypeById_returnsCompanyType() {
		Mockito.when(facilityTypeRepository.findById(1L)).thenReturn(Optional.of(getTestData().get(0)));
		Assertions.assertEquals(getTestData().get(0), serviceTypeService.getFacilityTypeById(1L));
	}

	@Test
	void testGetUserTypeById_throwsResourceNotFoundException() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> serviceTypeService.getFacilityTypeById(1L));
	}

	private List<FacilityType> getTestData() {
		List<FacilityType> facilityTypes = new ArrayList<>();
		facilityTypes.add(new FacilityType(1L, FacilityTypeEnum.VENUE));


		return facilityTypes;
	}

}
