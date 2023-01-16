package com.time_tracker.backendtime_tracker.Services.Projects;

import com.time_tracker.backendtime_tracker.Dtos.Project.ProjectDto;
import com.time_tracker.backendtime_tracker.Entities.Company;
import com.time_tracker.backendtime_tracker.Entities.Contractor;
import com.time_tracker.backendtime_tracker.Entities.Project;
import com.time_tracker.backendtime_tracker.Mapper.CompanyMapper;
import com.time_tracker.backendtime_tracker.Mapper.ContractorMapper;
import com.time_tracker.backendtime_tracker.Mapper.ProjectMapper;
import com.time_tracker.backendtime_tracker.Repositories.CompanyRepository;
import com.time_tracker.backendtime_tracker.Repositories.ContractorRepository;
import com.time_tracker.backendtime_tracker.Repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ContractorRepository contractorRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Project updateProject(Project project) throws Exception {

        Project updateProject = null;

        updateProject = projectRepository.findById(project.getId()).get();


        Optional<Contractor> contractor = contractorRepository.findById(project.getContractor().getId());
        if(!(contractor.isPresent())){
            throw new Exception("Contractor with given id does not exist");
        }

        Optional<Company> company = companyRepository.findById(project.getCompany().getId());
        if(!(company.isPresent())){
            throw new Exception("Given company name does not exist");
        }


        if(updateProject != null){
            updateProject = ProjectMapper.updateProjectFields(updateProject, project);
        }

        try{
            projectRepository.save(updateProject);
        }catch (Exception e){
            throw new Exception(e.getCause().getCause());
        }

        return updateProject;
    }

    @Override
    public void deleteProject(Long projectId) throws Exception {
        Optional<Project> contractor = projectRepository.findById(projectId);

        if(!(contractor.isPresent())){
            throw new Exception("Project with given id does not exist");
        }

        projectRepository.deleteById(projectId);

    }

    @Override
    public Project saveProject(Project project) throws Exception {
        Project resultedProject = null;

        Optional<Contractor> contractor = contractorRepository.findById(project.getContractor().getId());
        if(!(contractor.isPresent())){
            throw new Exception("Contractor with given id does not exist");
        }

        Optional<Company> company = companyRepository.findById(project.getCompany().getId());
        if(!(company.isPresent())){
            throw new Exception("Given company name does not exist");
        }

        try {
            resultedProject = new Project();
            resultedProject = projectRepository.save(new Project(null, project.getStartDate(), project.getEndDate(),
                    project.getContractorDailySalary(), company.get(),contractor.get(), project.getDescription()));
        }catch (Exception e){
            throw new Exception(e.getCause());
        }

        return resultedProject;
    }

    @Override
    public Project getSpecificProject(Long projectId) throws Exception {
        Optional<Project> project = projectRepository.findById(projectId);

        if(!(project.isPresent())){
            throw new Exception("Project with given id does not exist");
        }
        return project.get();
    }

    @Override
    public Set<ProjectDto> getAllProjects() {
        Iterable<Project> contractors = projectRepository.findAll();
        System.out.println("Service iterable");
        System.out.println(contractors);

        return ProjectMapper.castIterableProjectToProjectDto(contractors);
    }
}
