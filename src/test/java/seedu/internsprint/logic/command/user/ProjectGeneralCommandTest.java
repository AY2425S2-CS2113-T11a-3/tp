package seedu.internsprint.logic.command.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internsprint.model.userprofile.project.Project;
import seedu.internsprint.model.userprofile.project.GeneralProject;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class ProjectGeneralCommandTest {
    ProjectGeneralCommand projectGeneralCommand;
    HashMap<String,String> parameters;

    @BeforeEach
    void setUp() {
        projectGeneralCommand = new ProjectGeneralCommand();
        parameters = projectGeneralCommand.getParameters();
    }

    @Test
    void isValidParameters_provideCorrectMandatoryFlags_returnsValid() {
        parameters.put("/n","Name");
        parameters.put("/r","Role");
        parameters.put("/dept","Tech");
        parameters.put("/obj","Objective");
        parameters.put("/desc","Description");
        parameters.put("/dur","May-August");
        assertTrue(projectGeneralCommand.isValidParameters());
    }

    @Test
    void isValidParameters_provideMissingMandatoryFlags_returnsInvalid() {
        parameters.put("/n","Name");
        parameters.put("/r","Role");
        parameters.put("/obj","Objective");
        parameters.put("/desc","Description");
        assertFalse(projectGeneralCommand.isValidParameters());
    }

    @Test
    void execute_provideAllFlags_createsProject() {
        parameters.put("/n","Name");
        parameters.put("/r","Role");
        parameters.put("/dept","Tech");
        parameters.put("/obj","Objective");
        parameters.put("/desc","Description");
        parameters.put("/dur","May-August");
        Project project = projectGeneralCommand.createProject();
        assertEquals("Role", project.getRole());
        assertEquals("Name", project.getProjectName());
        assertEquals("Tech", ((GeneralProject) project).getDepartment());
        assertEquals("Objective", project.getObjectives());
        assertEquals("Description", project.getDescription());
        assertEquals("May-August", project.getDuration());
    }
}

