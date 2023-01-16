package com.time_tracker.backendtime_tracker.Dtos.Contractor;

import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContractorDtoDetails {
    ContractorDto contractorDto;
    Set<ContractorDtoProjects> contractorDtoProjectsSet = new HashSet<>();
}
