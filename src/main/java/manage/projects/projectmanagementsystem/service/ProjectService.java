package manage.projects.projectmanagementsystem.service;


import manage.projects.projectmanagementsystem.models.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAllProjects();

    Project getProjectById(Long projectId);

    Project createProject(Project project);

    Project updateProject(Long projectId, Project updatedProject);

    void deleteProject(Long projectId);
}
