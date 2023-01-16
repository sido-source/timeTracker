package com.time_tracker.backendtime_tracker.Dtos.Company;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDtoWithProject {
    Long contractId;
    Date contractStartDate;
    Date contractEndDate;
    String contractDescription;
    Float contractorDailySalary;
    String contractorName;
    String contractorSurname;
    String contractorPosition;
}
