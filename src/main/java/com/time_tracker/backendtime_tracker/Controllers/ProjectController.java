package com.time_tracker.backendtime_tracker.Controllers;

import com.time_tracker.backendtime_tracker.Entities.Contractor;
import com.time_tracker.backendtime_tracker.Entities.Project;
import com.time_tracker.backendtime_tracker.Repositories.ProjectRepository;
import com.time_tracker.backendtime_tracker.Services.Projects.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("update")
    public ResponseEntity<Project> updateProject(@RequestBody Project project) throws Exception {
        Project projectResult = new Project();

        try{
            projectResult = projectService.updateProject(project);
        } catch (Exception e) {
            return new ResponseEntity<Project>(projectResult, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Project>(projectResult, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<String> deleteProject(Integer projectId) throws Exception {

        try {
            projectService.deleteProject(projectId);
        } catch (Exception e) {
            new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @GetMapping("get")
    public ResponseEntity<Set<Project>> getAllProjects(){
        return new ResponseEntity<Set<Project>>(projectService.getAllProjects(),HttpStatus.OK);
    }

    @GetMapping("get/{projectId}")
    public ResponseEntity<Project> getSpecificProject(Integer projectId) throws Exception {

        Project project = null;

        try {
            project = projectService.getSpecificProject(projectId);
        } catch (Exception e) {
            return new ResponseEntity<Project>(project, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveProject(@RequestBody Project project){
        Project projectResuit = null;

        try {
            projectResuit = projectService.saveProject(project);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().getCause().getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Project has been saved", HttpStatus.OK);
    }
}
