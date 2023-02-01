package com.time_tracker.backendtime_tracker.Services.Company;

import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDto;
import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDtoDetails;

import java.util.Set;


public interface CompanyService {

    CompanyDto saveCompany(CompanyDto companyDto) throws Exception;

    void deleteCompany(Long companyId) throws Exception;

    Set<CompanyDto> getAllCompanies();

    CompanyDtoDetails getSpecificCompany(Long companyId) throws Exception;

    CompanyDto updateCompany(Long id, CompanyDto companyDto) throws Exception;

    Set<CompanyDto> getAllCompaniesForContractor(Long contractorId);
}
