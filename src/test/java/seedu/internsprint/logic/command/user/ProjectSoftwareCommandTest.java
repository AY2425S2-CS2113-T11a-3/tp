package seedu.internsprint.logic.command.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internsprint.model.userprofile.project.Project;
import seedu.internsprint.model.userprofile.project.SoftwareProject;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class ProjectSoftwareCommandTest {
    ProjectSoftwareCommand projectSoftwareCommand;
    HashMap<String,String> parameters;

    @BeforeEach
    void setUp() {
        projectSoftwareCommand = new ProjectSoftwareCommand();
        parameters = projectSoftwareCommand.getParameters();
    }

    @Test
    void isValidParameters_provideCorrectMandatoryFlags_returnsValid() {
        parameters.put("/n","Name");
        parameters.put("/r","Role");
        parameters.put("/desc","Description");
        parameters.put("/obj","Objective");
        parameters.put("/pro","Programming Languages");
        parameters.put("/dur","May-August");
        assertTrue(projectSoftwareCommand.isValidParameters());
    }
    @Test
    void isValidParameters_provideMissingMandatoryFlags_returnsInalid() {
        parameters.put("/n","Name");
        parameters.put("/r","Role");
        parameters.put("/obj","Objective");
        parameters.put("/desc","Description");
        assertFalse(projectSoftwareCommand.isValidParameters());
    }

    @Test
    void execute_provideAllFlags_createsProject() {
        parameters.put("/n","Name");
        parameters.put("/r","Role");
        parameters.put("/obj","Objective");
        parameters.put("/desc","Description");
        parameters.put("/dur","May-August");
        parameters.put("/pro","Programming Languages");
        Project project = projectSoftwareCommand.createProject();
        assertEquals("Role", project.getRole());
        assertEquals("Name", project.getProjectName());
        assertEquals(List.of("Programming Languages"), ((SoftwareProject) project).getProgrammingLanguages());
        assertEquals("Objective", project.getObjectives());
        assertEquals("Description", project.getDescription());
        assertEquals("May-August", project.getDuration());
    }
}
