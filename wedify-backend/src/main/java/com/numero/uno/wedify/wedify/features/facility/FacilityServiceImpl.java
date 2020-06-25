package com.numero.uno.wedify.wedify.features.facility;


import com.numero.uno.wedify.wedify.exception.ResourceAlreadyExistsException;
import com.numero.uno.wedify.wedify.exception.ResourceNotFoundException;
import com.numero.uno.wedify.wedify.features.company.Company;
import com.numero.uno.wedify.wedify.features.company.ICompanyService;
import com.numero.uno.wedify.wedify.features.image.IImageService;
import com.numero.uno.wedify.wedify.util.SecurityHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class FacilityServiceImpl implements IFacilityService {

    private final FacilityRepository facilityRepository;
    private final IImageService imageService;
    private final ICompanyService companyService;

    public FacilityServiceImpl(FacilityRepository facilityRepository, IImageService imageService, ICompanyService companyService) {
        this.facilityRepository = facilityRepository;
        this.imageService = imageService;
        this.companyService = companyService;
    }

    @Override
    public Facility getFacilityById(Long id) {
        Optional<Facility> facility = facilityRepository.findById(id);
        return facility.orElseGet(() -> facility.orElseThrow(() -> new ResourceNotFoundException(Facility.class.getSimpleName(), id)));
    }

    @Override
    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    @Override
    public List<Facility> getFacilitiesByCompany(Long companyId) {
        Company company = companyService.getCompanyById(companyId);

        return facilityRepository.findByCompany(company);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public void deleteFacility(Long id) {
        Facility facilityToDelete = getFacilityById(id);

        SecurityHelper.validatePrincipalIsCompany(facilityToDelete.getCompany().getId());

        facilityRepository.delete(facilityToDelete);
    }

    @Override
    public void validateFacilityDoesntExist(String name) {
        facilityRepository.findByName(name).ifPresent(s -> {
            throw new ResourceAlreadyExistsException(Facility.class, "name", name);
        });
    }

    @Override
    public void uploadImage(MultipartFile file, Long id) {
        Optional<Facility> facility = facilityRepository.findById(id);
        String imagename = imageService.uploadImageFacility(file, id);
        if (facility.isPresent()) {
            facility.get().setImage(imagename);
            facilityRepository.save(facility.get());
        }
    }

    @Override
    public void removeImage(String filename, Long id) {
        imageService.removeImageFacility(filename, id);
    }

    @Override
    public String getImages(Long id) {
        Optional<Facility> facility = facilityRepository.findById(id);
        if (facility.isPresent()) {
            return imageService.getImage("facility", id);
        } else {
            return "Not found";
        }
    }
}
