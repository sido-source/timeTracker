package com.time_tracker.backendtime_tracker.Mapper;

import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDtoWithProject;
import com.time_tracker.backendtime_tracker.Dtos.Project.ProjectDto;
import com.time_tracker.backendtime_tracker.Entities.Company;
import com.time_tracker.backendtime_tracker.Entities.Contractor;
import com.time_tracker.backendtime_tracker.Entities.Project;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ProjectMapper {

    public static Set<Project> castIterableProjectToSet(Iterable<Project> projects) {
        Set<Project> projectSet = new HashSet<>();
        for (Project project : projects){
            projectSet.add(project);
        }
        return projectSet;
    }

    public static Project updateProjectFields(Project oldProject, Project newPoject) {
        Float contractorDailySalary = (newPoject.getContractorDailySalary() != null) ? newPoject.getContractorDailySalary() : oldProject.getContractorDailySalary();
        Date startDate = newPoject.getStartDate() != null ? newPoject.getStartDate() : newPoject.getStartDate() ;
        Date endDate = newPoject.getEndDate() != null ? newPoject.getEndDate() : newPoject.getEndDate() ;
        String description = (newPoject.getDescription() != null)? newPoject.getDescription() : oldProject.getDescription(); ;
        Contractor contractor = ContractorMapper.updateCompanyFields(oldProject.getContractor(), newPoject.getContractor());
        Company company = CompanyMapper.updateCompanyFields(oldProject.getCompany(), newPoject.getCompany());

        return new Project(newPoject.getId(), startDate, endDate, contractorDailySalary, company, contractor, description);
    }

    public static CompanyDtoWithProject castProjectToCompanyDtoWithProject(Project project){
        CompanyDtoWithProject companyDtoWithProject = new CompanyDtoWithProject();

        companyDtoWithProject.setContractId(project.getId());
        companyDtoWithProject.setContractDescription(project.getDescription());
        companyDtoWithProject.setContractorDailySalary(project.getContractorDailySalary());
        companyDtoWithProject.setContractStartDate(project.getStartDate());
        companyDtoWithProject.setContractEndDate(project.getEndDate());
        companyDtoWithProject.setContractorName(project.getContractor().getName());
        companyDtoWithProject.setContractorSurname(project.getContractor().getSurname());
        companyDtoWithProject.setContractorPosition(project.getContractor().getPosition());

        return companyDtoWithProject;
    }

    public static ProjectDto castProjectToProjectDto(Project project){
        return new ProjectDto(project.getId(), project.getStartDate(), project.getEndDate(), project.getContractorDailySalary(), project.getContractor().getName(), project.getContractor().getSurname(), project.getCompany().getName(), project.getCompany().getIndustry());
    }
    public static Set<ProjectDto> castIterableProjectToProjectDto(Iterable<Project> projectIterable){
        Set<ProjectDto> projectDtoSet = new HashSet<>();

        for (Project project : projectIterable){
            projectDtoSet.add(castProjectToProjectDto(project));
        }

        return projectDtoSet;
    }
}
