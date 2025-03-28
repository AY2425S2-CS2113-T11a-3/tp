package seedu.internsprint.logic.command.internship;

import seedu.internsprint.model.internship.Internship;
import seedu.internsprint.model.internship.SoftwareInternship;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddSoftwareCommandTest {

    AddSoftwareInternshipCommand addSoftwareCommand;
    HashMap<String, String> parameters;

    @BeforeEach
    void setUp() {
        addSoftwareCommand = new AddSoftwareInternshipCommand();
        parameters = addSoftwareCommand.getParameters();
        parameters.put("/c", "Google");
        parameters.put("/r", "Software Engineer");
    }


    @Test
    void createInternship_validParameters_correctlyConstructed() {
        parameters.put("/tech", "Java, Python");
        addSoftwareCommand.setParameters(parameters);
        Internship internship = addSoftwareCommand.createInternship();
        assertEquals("Google", internship.getCompanyName());
        assertEquals("Software Engineer", internship.getRole());
        assertEquals("Java, Python", ((SoftwareInternship) internship).getTechStack());
        assertEquals("software", internship.getType());
    }

    @Test
    void createInternship_validOptionalParameters_correctlyConstructed() {
        parameters.put("/tech", "Java, Python");
        parameters.put("/eli", "Eligibility");
        parameters.put("/desc", "Description");
        parameters.put("/status", "Status");
        parameters.put("/ex", "Expectations");
        addSoftwareCommand.setParameters(parameters);

        Internship internship = addSoftwareCommand.createInternship();
        assertEquals("Google", internship.getCompanyName());
        assertEquals("Software Engineer", internship.getRole());
        assertEquals("Java, Python", ((SoftwareInternship) internship).getTechStack());
        assertEquals("Eligibility", internship.getEligibility());
        assertEquals("Description", internship.getDescription());
        assertEquals("Status", internship.getStatus());
        assertEquals("Expectations", internship.getExpectations());
    }

    @Test
    void createInternship_invalidTechStack_throwsException() {
        final String[] invalidTechStacks = {null, "", " ", "  "};
        for (String techStack : invalidTechStacks) {
            parameters.put("/tech", techStack);
            addSoftwareCommand.setParameters(parameters);
            assertThrows(IllegalArgumentException.class, addSoftwareCommand::createInternship);
        }
    }
}
