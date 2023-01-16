package com.time_tracker.backendtime_tracker.Dtos.Project;

import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDto;
import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ProjectDtoDetails {
    Long projectId;
    Date startDate;
    Date endDate;
    Float contractorDailySalary;
    ContractorDto contractorDto;
    CompanyDto companyDto;

}
