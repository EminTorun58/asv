package com.numero.uno.wedify.wedify.features.usertype;

import com.numero.uno.wedify.wedify.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Valid
public class UserTypeServiceImpl implements IUserTypeService {

    private final UserTypeRepository userTypeRepository;

    public UserTypeServiceImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public List<UserType> getAllUserTypes() {
        return userTypeRepository.findAll();
    }

    @Override
    public UserType getUserTypeById(Long id) throws ResourceNotFoundException {
        Optional<UserType> userType = userTypeRepository.findById(id);

        return userType.orElseGet(() -> userType.orElseThrow(() -> new ResourceNotFoundException(UserType.class.getSimpleName(), id)));
    }

    @Override
    public UserType getUserTypeByType(UserTypeEnum type) {

        Optional<UserType> userType = userTypeRepository.findByType(type);

        return userType.orElseGet(() -> userType.orElseThrow(() -> new ResourceNotFoundException(UserType.class.getSimpleName(), type)));
    }

}
