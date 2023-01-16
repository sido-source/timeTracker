package com.time_tracker.backendtime_tracker.Dtos.Company;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDtoDetails {
    @JsonProperty("companyInfo")
    CompanyDto companyDto;
    @JsonProperty("companyContracts")
    Set<CompanyDtoWithProject> companyDtoWithProjects = new HashSet<>();
}
