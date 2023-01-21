package com.time_tracker.backendtime_tracker.Dtos.Contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDto;
import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ContractDtoDetails {
    private Long contractId;
    private Date contractorStartDate;
    private Date contractorEndDate;
    private Float contractDailySalary;
    @JsonProperty("contractorInfo")
    private ContractorDto contractorDto;
    @JsonProperty("companyInfo")
    private CompanyDto companyDto;

}
