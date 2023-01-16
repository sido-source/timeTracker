package com.time_tracker.backendtime_tracker.Services.Company;

import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDto;
import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDtoDetails;
import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDtoWithProject;

import java.util.Set;


public interface CompanyService {

    CompanyDto saveCompany(CompanyDto companyDto) throws Exception;

    void deleteCompany(String companyName) throws Exception;

    Set<CompanyDto> getAllCompanies();

    CompanyDtoDetails getSpecificCompany(String companyName) throws Exception;

    CompanyDto updateCompany(CompanyDto companyDto) throws Exception;

}
