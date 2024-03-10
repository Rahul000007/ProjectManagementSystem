package manage.projects.projectmanagementsystem.service.impl;

import manage.projects.projectmanagementsystem.exceptionHandling.ResourceNotFoundException;
import manage.projects.projectmanagementsystem.models.Project;
import manage.projects.projectmanagementsystem.repository.ProjectRepository;
import manage.projects.projectmanagementsystem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", projectId));
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Long projectId, Project updatedProject) {
        Optional<Project> optionalExistingProject = projectRepository.findById(projectId);

        if (optionalExistingProject.isPresent()) {
            Project existingProject = optionalExistingProject.get();
            existingProject.setName(updatedProject.getName());
            existingProject.setDescription(updatedProject.getDescription());
            existingProject.setStartDate(updatedProject.getStartDate());
            existingProject.setEndDate(updatedProject.getEndDate());
            return projectRepository.save(existingProject);
        } else {
            throw new ResourceNotFoundException("Project", "id", projectId);
        }
    }

    @Override
    public void deleteProject(Long projectId) {
        if (projectRepository.existsById(projectId)) {
            projectRepository.deleteById(projectId);
        } else {
            throw new ResourceNotFoundException("Project", "id", projectId);
        }    }
}
