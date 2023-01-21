package com.time_tracker.backendtime_tracker.Dtos.Contract;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {
    private Long contractId;
    private Date contractStartDate;
    private Date contractEndDate;
    private Float contractDailySalary;
    private String contractDescription;
    private Long contractorId;
    private Long companyId;

}
