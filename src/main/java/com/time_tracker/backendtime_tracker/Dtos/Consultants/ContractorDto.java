package com.time_tracker.backendtime_tracker.Dtos.Consultants;

import com.time_tracker.backendtime_tracker.Entities.Project;

import java.util.Set;

public class ContractorDto {
    public String name;
    public String surname;
    public Set<Project> projects;
}
