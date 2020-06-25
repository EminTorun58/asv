package com.numero.uno.wedify.wedify.features.company;

import javax.validation.Valid;
import java.util.List;


public interface ICompanyService {
    Company saveCompany(@Valid Company company);

    Company getCompanyById(Long id);

    List<Company> getAllCompanies();

    Company updateCompany(@Valid Company company, Long companyId);

}
