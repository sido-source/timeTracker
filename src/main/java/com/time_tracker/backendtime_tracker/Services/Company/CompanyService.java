package com.time_tracker.backendtime_tracker.Services.Company;

import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDto;
import com.time_tracker.backendtime_tracker.Entities.Company;

import java.util.Set;


public interface CompanyService {

    CompanyDto saveCompany(CompanyDto companyDto) throws Exception;

    void deleteCompany(String companyName) throws Exception;

    Set<Company> getAllCompanies();

    CompanyDto getSpecificCompany(String comapnyName) throws Exception;

    // we can not declare this way -> updateCompany(String companyName), because later on we will need all the fields of object
    CompanyDto updateCompany(CompanyDto companyDto) throws Exception;
}
