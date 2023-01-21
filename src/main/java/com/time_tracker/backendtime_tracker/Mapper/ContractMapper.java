package com.time_tracker.backendtime_tracker.Mapper;

import com.time_tracker.backendtime_tracker.Dtos.Contract.ContractDto;
import com.time_tracker.backendtime_tracker.Entities.Company;
import com.time_tracker.backendtime_tracker.Entities.Contract;
import com.time_tracker.backendtime_tracker.Entities.Contractor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ContractMapper {

    public static Set<ContractDto> castIterableToContractDtoSet(Iterable<Contract> contracts) {
        Set<ContractDto> contractSet = new HashSet<>();

        for (Contract contract : contracts){
            contractSet.add(castContractToContractDto(contract));
        }
        return contractSet;
    }

    public static ContractDto castContractToContractDto(Contract contract){
        ContractDto contractDto = new ContractDto();

        contractDto.setContractId(contract.getId());
        contractDto.setContractStartDate(contract.getStartDate());
        contractDto.setContractEndDate(contract.getEndDate());
        contractDto.setContractDailySalary(contract.getDailySalary());
        contractDto.setContractDescription(contract.getDescription());
        contractDto.setContractorId(contract.getContractor().getId());
        contractDto.setCompanyId(contract.getCompany().getId());

        return contractDto;
    }

    public static ContractDto updateContractDtoFields(ContractDto oldContractDto, ContractDto newProjectDto) {
        Float contractorDailySalary = (newProjectDto.getContractDailySalary() != null) ? newProjectDto.getContractDailySalary() : oldContractDto.getContractDailySalary();
        Date startDate = newProjectDto.getContractStartDate() != null ? newProjectDto.getContractStartDate() : oldContractDto.getContractStartDate() ;
        Date endDate = newProjectDto.getContractEndDate() != null ? newProjectDto.getContractEndDate() : oldContractDto.getContractEndDate() ;
        String description = (newProjectDto.getContractDescription() != null)? newProjectDto.getContractDescription() : oldContractDto.getContractDescription() ;
        Long contractorId = newProjectDto.getContractorId() != null ? newProjectDto.getContractorId() : oldContractDto.getContractorId();
        Long companyId = newProjectDto.getCompanyId() != null ? newProjectDto.getCompanyId() : oldContractDto.getCompanyId();

        return new ContractDto(oldContractDto.getContractId(), startDate, endDate, contractorDailySalary, description, contractorId, companyId);
    }

    public static Contract castContractDtoToContract(ContractDto contractDto, Company company, Contractor contractor){
        Contract contract = new Contract();

        contract.setDailySalary(contractDto.getContractDailySalary());
        contract.setStartDate(contractDto.getContractStartDate());
        contract.setEndDate(contractDto.getContractEndDate());
        contract.setDescription(contractDto.getContractDescription());
        contract.setCompany(company);
        contract.setContractor(contractor);

        return contract;
    }
}
