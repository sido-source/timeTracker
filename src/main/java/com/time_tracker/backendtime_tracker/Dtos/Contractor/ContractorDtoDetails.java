package com.time_tracker.backendtime_tracker.Dtos.Contractor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContractorDtoDetails {
    @JsonProperty("contractorInfo")
    private ContractorDto contractorDto;
    @JsonProperty("contractorContracts")
    private Set<ContractorDtoWithContracts> contractorDtoWithContractsSet = new HashSet<>();
}
