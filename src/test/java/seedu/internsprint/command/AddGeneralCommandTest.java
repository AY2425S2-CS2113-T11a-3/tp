package seedu.internsprint.command;

import seedu.internsprint.internship.GeneralInternship;
import seedu.internsprint.internship.Internship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddGeneralCommandTest {

    @Test
    void createInternship_validParameters_correctlyConstructed() {
        AddGeneralCommand addGeneralCommand = new AddGeneralCommand();
        addGeneralCommand.parameters.put("/c", "Google");
        addGeneralCommand.parameters.put("/r", "Software Engineer");
        addGeneralCommand.parameters.put("/dept", "Engineering");
        Internship internship = addGeneralCommand.createInternship();
        assertEquals("Google", internship.getCompanyName());
        assertEquals("Software Engineer", internship.getRole());
        assertEquals("Engineering", ((GeneralInternship) internship).getDepartment());
        assertEquals("general", internship.getType());
    }

    @Test
    void createInternship_invalidCompanyName_throwsException() {
        final String[] invalidCompanyNames = {null, "", " ", "  "};
        for (String companyName : invalidCompanyNames) {
            AddGeneralCommand addGeneralCommand = new AddGeneralCommand();
            addGeneralCommand.parameters.put("/c", companyName);
            addGeneralCommand.parameters.put("/r", "Software Engineer");
            addGeneralCommand.parameters.put("/dept", "Engineering");
            assertThrows(IllegalArgumentException.class, addGeneralCommand::createInternship);
        }
    }

    @Test
    void createInternship_invalidRole_throwsException() {
        final String[] invalidRoles = {null, "", " ", "  "};
        for (String role : invalidRoles) {
            AddGeneralCommand addGeneralCommand = new AddGeneralCommand();
            addGeneralCommand.parameters.put("/c", "Google");
            addGeneralCommand.parameters.put("/r", role);
            addGeneralCommand.parameters.put("/dept", "Engineering");
            assertThrows(IllegalArgumentException.class, addGeneralCommand::createInternship);
        }
    }

    @Test
    void createInternship_invalidDepartment_throwsException() {
        final String[] invalidDepartments = {null, "", " ", "  "};
        for (String department : invalidDepartments) {
            AddGeneralCommand addGeneralCommand = new AddGeneralCommand();
            addGeneralCommand.parameters.put("/c", "Google");
            addGeneralCommand.parameters.put("/r", "Software Engineer");
            addGeneralCommand.parameters.put("/dept", department);
            assertThrows(IllegalArgumentException.class, addGeneralCommand::createInternship);
        }
    }
}
