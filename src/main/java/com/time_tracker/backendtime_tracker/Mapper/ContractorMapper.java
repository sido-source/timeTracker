package com.time_tracker.backendtime_tracker.Mapper;

import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDto;
import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDtoDetails;
import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDtoWithContracts;
import com.time_tracker.backendtime_tracker.Entities.Contractor;
import com.time_tracker.backendtime_tracker.Entities.Contract;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ContractorMapper {

    public static Set<ContractorDto> castIterableToContractorDtoSet(Iterable<Contractor> contractors){

        Set<ContractorDto> resultSet = new HashSet<>();

        for(Contractor contractor : contractors){
            resultSet.add(ContractorMapper.castContractorToContractorDto(contractor));
        }

        return resultSet;
    }

    public static Contractor updateCompanyFields(Contractor oldContractor, Contractor newContractor){
        Long id = oldContractor.getId();

        String name = Objects.isNull(newContractor.getName())? oldContractor.getName() : newContractor.getName() ;
        String surname = Objects.isNull(newContractor.getSurname())? oldContractor.getSurname() : newContractor.getSurname();
        String department = Objects.isNull(newContractor.getDepartment())? oldContractor.getDepartment() : newContractor.getDepartment();
        String position = Objects.isNull(newContractor.getPosition())? oldContractor.getPosition() : newContractor.getPosition();
        Long pesel = Objects.isNull(newContractor.getPesel())? oldContractor.getPesel() : newContractor.getPesel();

        return new Contractor(id, name, surname, department, position, pesel, oldContractor.getContracts());
    }

    public static ContractorDtoWithContracts castProjectToContractorDtoProjects(Contract contract){
        ContractorDtoWithContracts contractorDtoWithContracts = new ContractorDtoWithContracts();

        contractorDtoWithContracts.setContractId(contract.getId());
        contractorDtoWithContracts.setContractDescription(contract.getDescription());
        contractorDtoWithContracts.setContractStartDate(contract.getStartDate());
        contractorDtoWithContracts.setContractEndDate(contract.getEndDate());
        contractorDtoWithContracts.setCompanyName(contract.getCompany().getName());
        contractorDtoWithContracts.setCompanyIndustry(contract.getCompany().getIndustry());
        contractorDtoWithContracts.setContractDailySalary(contract.getDailySalary());

        return contractorDtoWithContracts;
    }

    public static Contractor castContractorDtoToContractor(ContractorDto contractorDto){
        return new Contractor(contractorDto.getId(), contractorDto.getName(), contractorDto.getSurname(), contractorDto.getDepartment(),contractorDto.getPosition(), contractorDto.getPesel(), null);
    }

    public static ContractorDto castContractorToContractorDto(Contractor contractor){
        return new ContractorDto(contractor.getId(), contractor.getName(), contractor.getSurname(), contractor.getPosition(), contractor.getDepartment(), contractor.getPesel());
    }

    public static ContractorDtoDetails castContractorToContractorDtoDetails(Contractor contractor){
        ContractorDto contractorDto = castContractorToContractorDto(contractor);
        Set<ContractorDtoWithContracts> contractorDtoWithContractsSet = new HashSet<>();

        for (Contract contract : contractor.getContracts()){
            contractorDtoWithContractsSet.add(castProjectToContractorDtoProjects(contract));
        }

        return new ContractorDtoDetails(contractorDto, contractorDtoWithContractsSet);
    }
}
