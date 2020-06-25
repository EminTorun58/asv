package com.numero.uno.wedify.wedify.features.user;

import com.numero.uno.wedify.wedify.exception.ResourceAlreadyExistsException;
import com.numero.uno.wedify.wedify.exception.ResourceNotFoundException;
import com.numero.uno.wedify.wedify.security.UserPrincipal;
import com.numero.uno.wedify.wedify.util.SecurityHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.orElseGet(() -> user.orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id)));
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        return user.orElseGet(() -> user.orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), email)));
    }

    @Override
    public void validateUserDoesntExist(String email) {
        userRepository.findByEmail(email).ifPresent(s -> {
            throw new ResourceAlreadyExistsException(User.class, "email", email);
        });
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public User getUserByAuthentication() {
        UserPrincipal userPrincipal = SecurityHelper.getPrincipal();
        Optional<User> user = userRepository.findById(userPrincipal.getId());
        return user.orElseGet(() -> user.orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), userPrincipal.getId())));
    }

    @Override
    @PreAuthorize("isAuthenticated() and authentication.principal.id == #id")
    public void deleteUser(Long id) {
        User userToDelete = getUserById(id);
        userRepository.delete(userToDelete);
    }
}
