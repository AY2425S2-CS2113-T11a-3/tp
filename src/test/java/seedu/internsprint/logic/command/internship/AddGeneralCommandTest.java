package seedu.internsprint.logic.command.internship;

import seedu.internsprint.model.internship.GeneralInternship;
import seedu.internsprint.model.internship.Internship;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddGeneralCommandTest {

    AddGeneralInternshipCommand addGeneralCommand;
    HashMap<String,String> parameters;

    @BeforeEach
    void setUp() {
        addGeneralCommand = new AddGeneralInternshipCommand();
        parameters = addGeneralCommand.getParameters();
        parameters.put("/c", "Google");
        parameters.put("/r", "Software Engineer");
    }

    @Test
    void createInternship_validParameters_correctlyConstructed() {
        parameters.put("/dept", "Engineering");
        addGeneralCommand.setParameters(parameters);
        Internship internship = addGeneralCommand.createInternship();
        assertEquals("Google", internship.getCompanyName());
        assertEquals("Software Engineer", internship.getRole());
        assertEquals("Engineering", ((GeneralInternship) internship).getDepartment());
        assertEquals("general", internship.getType());
    }

    @Test
    void createInternship_validOptionalParameters_correctlyConstructed() {
        parameters.put("/dept", "Engineering");
        parameters.put("/eli", "NUS");
        parameters.put("/desc", "Software Engineer Intern");
        parameters.put("/status", "Open");
        parameters.put("/ex", "Software Engineer Intern");
        addGeneralCommand.setParameters(parameters);

        Internship internship = addGeneralCommand.createInternship();
        assertEquals("Google", internship.getCompanyName());
        assertEquals("Software Engineer", internship.getRole());
        assertEquals("Engineering", ((GeneralInternship) internship).getDepartment());
        assertEquals("NUS", internship.getEligibility());
        assertEquals("Software Engineer Intern", internship.getDescription());
        assertEquals("Open", internship.getStatus());
        assertEquals("Software Engineer Intern", internship.getExpectations());
        assertEquals("general", internship.getType());
    }

    @Test
    void createInternship_invalidCompanyName_throwsException() {
        final String[] invalidCompanyNames = {null, "", " ", "  "};
        for (String companyName : invalidCompanyNames) {
            AddGeneralInternshipCommand addGeneralCommand = new AddGeneralInternshipCommand();
            HashMap<String, String> parameters = addGeneralCommand.getParameters();
            parameters.put("/c", companyName);
            parameters.put("/r", "Software Engineer");
            parameters.put("/dept", "Engineering");
            addGeneralCommand.setParameters(parameters);
            assertThrows(IllegalArgumentException.class, addGeneralCommand::createInternship);
        }
    }

    @Test
    void createInternship_invalidRole_throwsException() {
        final String[] invalidRoles = {null, "", " ", "  "};
        for (String role : invalidRoles) {
            AddGeneralInternshipCommand addGeneralCommand = new AddGeneralInternshipCommand();
            HashMap<String, String> parameters = addGeneralCommand.getParameters();
            parameters.put("/c", "Google");
            parameters.put("/r", role);
            parameters.put("/dept", "Engineering");
            addGeneralCommand.setParameters(parameters);
            assertThrows(IllegalArgumentException.class, addGeneralCommand::createInternship);
        }
    }

    @Test
    void createInternship_invalidDepartment_throwsException() {
        final String[] invalidDepartments = {null, "", " ", "  "};
        for (String department : invalidDepartments) {
            parameters.put("/dept", department);
            addGeneralCommand.setParameters(parameters);
            assertThrows(IllegalArgumentException.class, addGeneralCommand::createInternship);
        }
    }
}
