package com.abans.JobApp.company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
    Company getCompanyById(Long id);
    void addCompany(Company company);
    boolean updateCompanyById(Long id, Company updatedCompany);
    boolean deleteCompanyById(Long id);

}
