package com.numero.uno.wedify.wedify.features.facilitytype;

import com.numero.uno.wedify.wedify.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class FacilityTypeServiceImpl implements IFacilityTypeService {

    private final FacilityTypeRepository facilityTypeRepository;

    public FacilityTypeServiceImpl(FacilityTypeRepository facilityTypeRepository) {
        this.facilityTypeRepository = facilityTypeRepository;
    }

    @Override
    public List<FacilityType> getAllFacilityTypes() {
        return facilityTypeRepository.findAll();
    }

    @Override
    public FacilityType getFacilityTypeById(Long id) {
        Optional<FacilityType> userType = facilityTypeRepository.findById(id);
        return userType.orElseGet(() -> userType.orElseThrow(() -> new ResourceNotFoundException(FacilityType.class.getSimpleName(), id)));
    }

    @Override
    public FacilityType getFacilityTypeByType(FacilityTypeEnum type) {
        Optional<FacilityType> userType = facilityTypeRepository.findByType(type);
        return userType.orElseGet(() -> userType.orElseThrow(() -> new ResourceNotFoundException(FacilityType.class.getSimpleName(), type)));
    }

}
