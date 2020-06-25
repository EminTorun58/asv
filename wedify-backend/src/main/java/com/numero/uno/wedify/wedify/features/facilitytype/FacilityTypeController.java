package com.numero.uno.wedify.wedify.features.facilitytype;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FacilityTypeController {

	private final IFacilityTypeService facilityTypeService;

	public FacilityTypeController(IFacilityTypeService facilityTypeService) {
		this.facilityTypeService = facilityTypeService;
	}

	@GetMapping(path = "/facilities/types/{id}")
	@ResponseStatus(HttpStatus.OK)
	public FacilityType getFacilityTypeById(@PathVariable Long id) {

		return facilityTypeService.getFacilityTypeById(id);
	}

	@GetMapping(path = "/facilities/types")
	@ResponseStatus(HttpStatus.OK)
	public List<FacilityType> getAllFacilityTypes() {

		return facilityTypeService.getAllFacilityTypes();
	}

}
