package com.time_tracker.backendtime_tracker.Mapper;

import com.time_tracker.backendtime_tracker.Dtos.Consultants.ContractorDto;
import com.time_tracker.backendtime_tracker.Entities.Company;
import com.time_tracker.backendtime_tracker.Entities.Contractor;
import com.time_tracker.backendtime_tracker.Entities.Project;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ContractorMapper {

    public static Set<Contractor> castIterableCompanyToSet(Iterable<Contractor> contractors){

        Set<Contractor> resultSet = new HashSet<>();

        for(Contractor contractor : contractors){
            resultSet.add(contractor);
        }

        return resultSet;
    }

    public static Contractor updateCompanyFields(Contractor oldContractor, Contractor newContractor){
        Integer id = oldContractor.getId();

        String name = Objects.isNull(newContractor.getName())? oldContractor.getName() : newContractor.getName() ;
        String surname = Objects.isNull(newContractor.getSurname())? oldContractor.getSurname() : newContractor.getSurname();
        String department = Objects.isNull(newContractor.getDepartment())? oldContractor.getDepartment() : newContractor.getDepartment();
        String position = Objects.isNull(newContractor.getPosition())? oldContractor.getPosition() : newContractor.getPosition();

        return new Contractor(id, name, surname, department, position, oldContractor.getProjects());
    }

    public static ContractorDto mapProject(Contractor contractor){
        ContractorDto contractorDto = new ContractorDto();

        Set<Project> projects = contractor.getProjects();

        contractorDto.projects = projects;

        contractorDto.name = contractor.getName();
        contractorDto.surname = contractor.getSurname();

        return contractorDto;
    }
}
