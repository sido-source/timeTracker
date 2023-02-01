package com.time_tracker.backendtime_tracker.Services.Contractor;

import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDto;
import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDto;
import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDtoDetails;
import com.time_tracker.backendtime_tracker.Entities.Contractor;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface ContractorService {
    public ContractorDto updateContractor(Long contractId, ContractorDto contractorDto) throws Exception;

    public void deleteContractor(Long contractorId) throws Exception;

    public ContractorDto saveContractor(ContractorDto contractorDto) throws Exception;

    public ContractorDtoDetails getSpecificContractor(Long contractorId) throws Exception;

    public Set<ContractorDto> getAllContractors();

    Set<ContractorDto> getAllContractorsForCompany(Long companyId);
}
