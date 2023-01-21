package com.time_tracker.backendtime_tracker.Dtos.Company;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private Long id;
    private String name;
    private String industry;
    private Integer foundedYear;
    private Integer budget;
}
