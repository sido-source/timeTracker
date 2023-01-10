package com.time_tracker.backendtime_tracker.Mapper;

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
        Float dailySalary = (newPoject.getDailySalary() != null) ? newPoject.getDailySalary() : oldProject.getDailySalary();
        Date startDate = newPoject.getStartDate() != null ? newPoject.getStartDate() : newPoject.getStartDate() ;
        Date endDate = newPoject.getEndDate() != null ? newPoject.getEndDate() : newPoject.getEndDate() ;
        String description = (newPoject.getDescription() != null)? newPoject.getDescription() : oldProject.getDescription(); ;
        Contractor contractor = ContractorMapper.updateCompanyFields(oldProject.getContractor(), newPoject.getContractor());
        Company company = CompanyMapper.updateCompanyFields(oldProject.getCompany(), newPoject.getCompany());

        return new Project(newPoject.getId(), startDate, endDate, dailySalary, company, contractor, description);
    }
}
