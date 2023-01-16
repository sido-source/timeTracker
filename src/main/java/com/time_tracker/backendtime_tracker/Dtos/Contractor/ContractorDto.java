package com.time_tracker.backendtime_tracker.Dtos.Contractor;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContractorDto {
    Long id;
    String name;
    String surname;
    String position;
    String department;
}
