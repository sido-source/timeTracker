package com.time_tracker.backendtime_tracker.Dtos.Contractor;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContractorDtoProjects {
    Long projectId;
    Date startDate;
    Date endDate;
    String description;
    Float contractorDailySalary;
    String companyName;
    String companyIndustry;

}
