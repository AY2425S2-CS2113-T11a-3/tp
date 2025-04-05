package seedu.internsprint.logic.command.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internsprint.model.userprofile.project.HardwareProject;
import seedu.internsprint.model.userprofile.project.Project;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ProjectHardwareCommandTest {
    ProjectHardwareCommand projectHardwareCommand;
    HashMap<String,String> parameters;

    @BeforeEach
    void setUp() {
        projectHardwareCommand = new ProjectHardwareCommand();
        parameters = projectHardwareCommand.getParameters();
    }

    @Test
    void isValidParameters_provideCorrectMandatoryFlags_returnsValid() {
        parameters.put("/n","Team Project for EE2026");
        parameters.put("/r","Ui Developer");
        parameters.put("/hcomp","Basys Board");
        parameters.put("/obj","To get an A+");
        parameters.put("/desc","Worked at creating pixel art for the UI");
        parameters.put("/dur","May-August");
        assertTrue(projectHardwareCommand.isValidParameters());
    }
    @Test
    void isValidParameters_provideMissingMandatoryFlags_returnsInalid() {
        parameters.put("/n","Team Project for EE2026");
        parameters.put("/r","Ui Developer");
        parameters.put("/hcomp","Basys Board");
        parameters.put("/obj","To get an A+");
        parameters.put("/desc","Worked at creating pixel art for the UI");
        assertFalse(projectHardwareCommand.isValidParameters());
    }

    @Test
    void execute_provideAllFlags_createsProject() {
        parameters.put("/n","Team Project for EE2026");
        parameters.put("/r","Ui Developer");
        parameters.put("/hcomp","Basys Board");
        parameters.put("/obj","To get an A+");
        parameters.put("/desc","Worked at creating pixel art for the UI");
        parameters.put("/dur","May-August");
        Project project = projectHardwareCommand.createProject();
        assertEquals("Ui Developer", project.getRole());
        assertEquals("Team Project for EE2026", project.getProjectName());
        assertEquals(List.of("Basys Board"), ((HardwareProject) project).getHardwareComponents());
        assertEquals("To get an A+", project.getObjectives());
        assertEquals("Worked at creating pixel art for the UI", project.getDescription());
        assertEquals("May-August", project.getDuration());
    }
}
