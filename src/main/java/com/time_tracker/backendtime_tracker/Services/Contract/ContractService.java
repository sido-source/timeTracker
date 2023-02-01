package com.time_tracker.backendtime_tracker.Services.Contract;

import com.time_tracker.backendtime_tracker.Dtos.Contract.ContractDto;
import com.time_tracker.backendtime_tracker.Dtos.Contract.ContractDtoDetails;
import com.time_tracker.backendtime_tracker.Entities.Contract;

import java.util.Set;

public interface ContractService {
    public ContractDto updateContract(Long contractorId, ContractDto contractorDto) throws Exception;

    public void deleteContract(Long contractId) throws Exception;

    public Contract saveContract(ContractDto contractorDto) throws Exception;

    public Contract getSpecificContract(Long contractorId) throws Exception;

    public Set<ContractDto> getAllContracts();

    public Set<ContractDto> getAllContractsForCompany(Long companyId);
    public Set<ContractDto> getAllContractsForContractor(Long contractorId);
}
