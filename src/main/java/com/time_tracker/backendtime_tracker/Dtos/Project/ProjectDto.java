package com.time_tracker.backendtime_tracker.Dtos.Project;

import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDto;
import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProjectDto {
    Long projectId;
    Date startDate;
    Date endDate;
    Float contractorcontractorDailySalary;
    String contractorName;
    String contractSurname;
    String companyName;
    String companyIndustry;
}
