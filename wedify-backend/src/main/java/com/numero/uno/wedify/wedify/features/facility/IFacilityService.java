package com.numero.uno.wedify.wedify.features.facility;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFacilityService {

	Facility getFacilityById(Long id);

	List<Facility> getAllFacilities();

	List<Facility> getFacilitiesByCompany(Long companyId);

	void deleteFacility(Long id);

	void validateFacilityDoesntExist(String name);

	void uploadImage(MultipartFile file, Long id);

	void removeImage(String filename, Long id);

	String getImages(Long id);
}
