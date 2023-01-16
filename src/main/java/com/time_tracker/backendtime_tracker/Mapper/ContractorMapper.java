package com.time_tracker.backendtime_tracker.Mapper;

import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDto;
import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDtoDetails;
import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDtoProjects;
import com.time_tracker.backendtime_tracker.Entities.Contractor;
import com.time_tracker.backendtime_tracker.Entities.Project;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ContractorMapper {

    public static Set<ContractorDto> castIterableCompanyToSet(Iterable<Contractor> contractors){

        Set<ContractorDto> resultSet = new HashSet<>();

        for(Contractor contractor : contractors){
            resultSet.add(ContractorMapper.castContractToContractorDto(contractor));
        }

        return resultSet;
    }

    public static Contractor updateCompanyFields(Contractor oldContractor, Contractor newContractor){
        Long id = oldContractor.getId();

        String name = Objects.isNull(newContractor.getName())? oldContractor.getName() : newContractor.getName() ;
        String surname = Objects.isNull(newContractor.getSurname())? oldContractor.getSurname() : newContractor.getSurname();
        String department = Objects.isNull(newContractor.getDepartment())? oldContractor.getDepartment() : newContractor.getDepartment();
        String position = Objects.isNull(newContractor.getPosition())? oldContractor.getPosition() : newContractor.getPosition();

        return new Contractor(id, name, surname, department, position, oldContractor.getProjects());
    }

    public static ContractorDtoProjects castContractorProjectToContractorDtoProjects(Project project){
        ContractorDtoProjects contractorDtoProjects = new ContractorDtoProjects();

        contractorDtoProjects.setProjectId(project.getId());
        contractorDtoProjects.setDescription(project.getDescription());
        contractorDtoProjects.setStartDate(project.getStartDate());
        contractorDtoProjects.setEndDate(project.getEndDate());
        contractorDtoProjects.setCompanyName(project.getCompany().getName());
        contractorDtoProjects.setCompanyIndustry(project.getCompany().getIndustry());
        contractorDtoProjects.setContractorDailySalary(project.getContractorDailySalary());

        return contractorDtoProjects;
    }

    public static Contractor castContractorDtoToContractor(ContractorDto contractorDto){
        return new Contractor(contractorDto.getId(), contractorDto.getName(), contractorDto.getSurname(), contractorDto.getPosition(), contractorDto.getDepartment(), null);
    }

    public static ContractorDto castContractToContractorDto(Contractor conntractor){
        return new ContractorDto(conntractor.getId(), conntractor.getName(), conntractor.getSurname(), conntractor.getPosition(), conntractor.getDepartment());
    }

    public static ContractorDtoDetails castContractorToContractorDtoDetails(Contractor contractor){
        ContractorDto contractorDto = new ContractorDto(contractor.getId(), contractor.getName(), contractor.getSurname(), contractor.getPosition(), contractor.getDepartment());
        Set<ContractorDtoProjects> contractorDtoProjectsSet = new HashSet<>();

        for (Project project : contractor.getProjects()){
            contractorDtoProjectsSet.add(castContractorProjectToContractorDtoProjects(project));
        }

        return new ContractorDtoDetails(contractorDto, contractorDtoProjectsSet);
    }
}
