package com.numero.uno.wedify.wedify.features.company;

import com.numero.uno.wedify.wedify.features.image.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    private final ICompanyService companyService;

    @Autowired
    public CompanyController(ICompanyService companyService) {
        this.companyService = companyService;
    }

	@PostMapping(path = "/users/companies", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Company registerCompany(@Validated({Company.Create.class}) @RequestBody Company company) {

        return companyService.saveCompany(company);
    }

    @GetMapping(path = "/users/companies/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Company getCompanyById(@PathVariable Long id) {

        return companyService.getCompanyById(id);
    }

    @GetMapping(path = "/users/companies")
	@ResponseStatus(HttpStatus.OK)
	public List<Company> getAllCompanies() {

		return companyService.getAllCompanies();
	}

	@PutMapping(path = "/users/companies/{id}")
	@PreAuthorize("isAuthenticated()")
	@ResponseStatus(HttpStatus.OK)
	public Company updateCompany(@Validated({Company.Update.class}) @RequestBody Company company, @PathVariable Long id) {

		return companyService.updateCompany(company, id);
	}
}
