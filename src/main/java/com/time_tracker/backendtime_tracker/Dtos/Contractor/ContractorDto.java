package com.time_tracker.backendtime_tracker.Dtos.Contractor;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContractorDto {
    private Long id;
    private String name;
    private String surname;
    private String position;
    private String department;
    private Long pesel;
}
