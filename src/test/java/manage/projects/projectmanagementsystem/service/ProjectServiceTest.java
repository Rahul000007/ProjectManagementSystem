package manage.projects.projectmanagementsystem.service;

import manage.projects.projectmanagementsystem.models.Project;
import manage.projects.projectmanagementsystem.repository.ProjectRepository;
import manage.projects.projectmanagementsystem.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    void getAllProjects() {
        Project project1 = new Project();
        project1.setId(1L);
        project1.setName("Project 1");
        project1.setDescription("Description 1");
        project1.setStartDate(LocalDate.of(2022, 1, 1));
        project1.setEndDate(LocalDate.of(2022, 2, 1));

        Project project2 = new Project();
        project2.setId(2L);
        project2.setName("Project 2");
        project2.setDescription("Description 2");
        project2.setStartDate(LocalDate.of(2022, 2, 1));
        project2.setEndDate(LocalDate.of(2022, 3, 1));

        List<Project> mockProjects = Arrays.asList(project1, project2);

        when(projectRepository.findAll()).thenReturn(mockProjects);

        assertEquals(mockProjects, projectService.getAllProjects());
    }

    @Test
    void getProjectById() {

        Project mockProject = new Project();
        mockProject.setId(1L);
        mockProject.setName("Mock Project");
        mockProject.setDescription("Mock project description");
        mockProject.setStartDate(LocalDate.of(2022, 1, 1));
        mockProject.setEndDate(LocalDate.of(2022, 12, 31));

        when(projectRepository.findById(1L)).thenReturn(Optional.of(mockProject));
        assertEquals(mockProject, projectService.getProjectById(1L));
    }


    @Test
    void createProject() {
        Project mockProject = new Project();

        mockProject.setName("New Project");
        mockProject.setDescription("New project description");
        mockProject.setStartDate(LocalDate.of(2022, 1, 1));
        mockProject.setEndDate(LocalDate.of(2022, 12, 31));

        when(projectRepository.save(mockProject)).thenReturn(mockProject);

        assertEquals(mockProject, projectService.createProject(mockProject));
    }

    @Test
    void updateProject() {

        Project existingProject = new Project();
        existingProject.setId(1L);
        existingProject.setName("Existing Project");
        existingProject.setDescription("Existing project description");
        existingProject.setStartDate(LocalDate.of(2022, 1, 1));
        existingProject.setEndDate(LocalDate.of(2022, 12, 31));

        Project updatedProject = new Project();
        updatedProject.setName("Updated Project");
        updatedProject.setDescription("Updated project description");
        updatedProject.setStartDate(LocalDate.of(2022, 2, 1));
        updatedProject.setEndDate(LocalDate.of(2022, 12, 30));

        when(projectRepository.findById(1L)).thenReturn(Optional.of(existingProject));
        when(projectRepository.save(existingProject)).thenReturn(existingProject);

        Project result = projectService.updateProject(1L, updatedProject);

        assertEquals("Updated Project", result.getName());
        assertEquals("Updated project description", result.getDescription());
        assertEquals(LocalDate.of(2022, 2, 1), result.getStartDate());
        assertEquals(LocalDate.of(2022, 12, 30), result.getEndDate());
    }


    @Test
    void deleteProjectExisting() {

        Project project = new Project();
        project.setId(1L);
        project.setName("Project");
        project.setDescription("project description");
        project.setStartDate(LocalDate.of(2022, 1, 1));
        project.setEndDate(LocalDate.of(2022, 12, 31));
        when(projectRepository.existsById(1L)).thenReturn(true);
        projectService.deleteProject(1L);
        verify(projectRepository, times(1)).deleteById(1L);

    }

}

