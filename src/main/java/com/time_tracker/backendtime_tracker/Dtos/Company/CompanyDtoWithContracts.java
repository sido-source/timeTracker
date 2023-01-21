package com.time_tracker.backendtime_tracker.Dtos.Company;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDtoWithContracts {
    private Long contractId;
    private Date contractStartDate;
    private Date contractEndDate;
    private String contractDescription;
    private Float contractorDailySalary;
    private String contractorName;
    private String contractorSurname;
    private String contractorPosition;
    private Long contractorPesel;
}
