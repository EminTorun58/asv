package com.numero.uno.wedify.wedify.features.facilitytype;

import java.util.List;

public interface IFacilityTypeService {

	List<FacilityType> getAllFacilityTypes();

	FacilityType getFacilityTypeById(Long id);

	FacilityType getFacilityTypeByType(FacilityTypeEnum type);

}
