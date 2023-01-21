package com.time_tracker.backendtime_tracker.Dtos.Contractor;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContractorDtoWithContracts {
    private Long contractId;
    private Date contractStartDate;
    private Date contractEndDate;
    private String contractDescription;
    private Float contractDailySalary;
    private String companyName;
    private String companyIndustry;

}
