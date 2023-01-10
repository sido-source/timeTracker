package com.time_tracker.backendtime_tracker.Services.Projects;

import com.time_tracker.backendtime_tracker.Entities.Contractor;
import com.time_tracker.backendtime_tracker.Entities.Project;

import java.util.Set;

public interface ProjectService  {
    public Project updateProject(Project project) throws Exception;

    public void deleteProject(Integer projectId) throws Exception;

    public Project saveProject(Project project) throws Exception;

    public Project getSpecificProject(Integer projectId) throws Exception;

    public Set<Project> getAllProjects();
}
