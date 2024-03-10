package manage.projects.projectmanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import manage.projects.projectmanagementsystem.models.Project;
import manage.projects.projectmanagementsystem.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Autowired
    private ObjectMapper objectMapper;

    Project project1 = new Project();
    Project project2 = new Project();
    List<Project> projectList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        project1.setId(1L);
        project1.setName("Project One");
        project1.setDescription("Description One");
        project1.setStartDate(LocalDate.of(2022, 1, 1));
        project1.setEndDate(LocalDate.of(2022, 1, 1));

        project2.setId(2L);
        project2.setName("Project One");
        project2.setDescription("Description One");
        project2.setStartDate(LocalDate.of(2022, 1, 1));
        project2.setEndDate(LocalDate.of(2022, 1, 1));

        projectList.add(project1);
        projectList.add(project2);

    }

    @Test
    void getProjectById() throws Exception {
        when(projectService.getProjectById(1L)).thenReturn(project1);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/project/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void getAllProjects() throws Exception {
        when(projectService.getAllProjects()).thenReturn(projectList);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/project")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(projectList)));
    }

    @Test
    void deleteProject() throws Exception {
        doNothing().when(projectService).deleteProject(1L);
        mockMvc.perform(delete("/project/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void createProject() throws Exception {

        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String projectJson = ow.writeValueAsString(project1);

        when(projectService.createProject(project1)).thenReturn(project1);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/project")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projectJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateProject() throws Exception {

        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String projectJson = ow.writeValueAsString(project1);

        when(projectService.updateProject(1L, project1)).thenReturn(project1);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/project/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projectJson))
                .andExpect(status().isOk());
    }

}

