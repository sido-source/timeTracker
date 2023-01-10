package com.time_tracker.backendtime_tracker.Dtos;

import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OutcommingResponses {
 private String status;
 private String operation;
 Set<CompanyDto> companies;
 CompanyDto companyDto;
}
