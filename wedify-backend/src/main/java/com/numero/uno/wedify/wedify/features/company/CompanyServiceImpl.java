package com.numero.uno.wedify.wedify.features.company;

import com.numero.uno.wedify.wedify.exception.ResourceAlreadyExistsException;
import com.numero.uno.wedify.wedify.exception.ResourceNotFoundException;
import com.numero.uno.wedify.wedify.features.user.IUserService;
import com.numero.uno.wedify.wedify.features.usertype.IUserTypeService;
import com.numero.uno.wedify.wedify.features.usertype.UserTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepository;

    private final IUserService userService;

    private final IUserTypeService userTypeService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, IUserService userService, IUserTypeService userTypeService, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.userService = userService;
        this.userTypeService = userTypeService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Persist a Company
     *
     * @param company Company to be persisted
     * @return Company The company that has been saved.
     */
    @Override
    @Validated({Company.Create.class})
    public Company saveCompany(@Valid Company company) {

        // Validate if the company doesn't exist yet.
        validateCompanyAlreadyExists(company.getEmail(), company.getKvk());

        // Encrypt the password.
        company.setPassword(passwordEncoder.encode(company.getPassword()));

        // Set the user type to company.
        company.setUserType(userTypeService.getUserTypeByType(UserTypeEnum.COMPANY));

        return companyRepository.save(company);
    }

    /**
     * Checks if a company already exists using the email and kvk.
     *
     * @param email String email of the company
     * @param kvk   String kvk of the company
     */
    private void validateCompanyAlreadyExists(String email, String kvk) {
        userService.validateUserDoesntExist(email);
        companyRepository.findByKvk(kvk).ifPresent(company -> {
            throw new ResourceAlreadyExistsException(Company.class, "kvk", kvk);
        });
    }

    /**
     * @param id Long id of the company
     * @return Company the company that is requested.
     */
    @Override
    public Company getCompanyById(Long id) {

        // Get the company from the repository and validate if the company exists.
        Optional<Company> company = companyRepository.findById(id);
        return company.orElseGet(() -> company.orElseThrow(() -> new ResourceNotFoundException(Company.class.getSimpleName(), id)));
    }

    /**
     * @return List<Company> A list of all the companies.
     */
    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    @PreAuthorize("isAuthenticated() and authentication.principal.id == #companyId")
    @Validated(Company.Update.class)
    public Company updateCompany(Company company, Long companyId) {

        // Get the company that needs to be updated.
        Company oldCompany = getCompanyById(companyId);

        // Set the id of the updated company.
        company.setId(oldCompany.getId());

        // If password was changed encode it. Otherwise keep the old password.
        if (company.getPassword() == null) company.setPassword(oldCompany.getPassword());
        else company.setPassword(passwordEncoder.encode(company.getPassword()));

        // Set the user type.
        company.setUserType(userTypeService.getUserTypeByType(UserTypeEnum.COMPANY));

        return companyRepository.save(company);
    }

}
