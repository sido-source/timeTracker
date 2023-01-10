package com.time_tracker.backendtime_tracker.Dtos.Company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private String name;
    private String industry;
    private Integer foundedYear;
    private Integer budget;
}
