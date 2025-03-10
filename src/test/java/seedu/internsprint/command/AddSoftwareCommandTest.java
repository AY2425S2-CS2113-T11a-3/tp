package seedu.internsprint.command;

import seedu.internsprint.internship.Internship;
import seedu.internsprint.internship.SoftwareInternship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddSoftwareCommandTest {

    @Test
    void createInternship_validParameters_correctlyConstructed() {
        AddSoftwareCommand addSoftwareCommand = new AddSoftwareCommand();
        addSoftwareCommand.parameters.put("/c", "Google");
        addSoftwareCommand.parameters.put("/r", "Software Engineer");
        addSoftwareCommand.parameters.put("/tech", "Java, Python");
        Internship internship = addSoftwareCommand.createInternship();
        assertEquals("Google", internship.getCompanyName());
        assertEquals("Software Engineer", internship.getRole());
        assertEquals("Java, Python", ((SoftwareInternship) internship).getTechStack());
        assertEquals("software", internship.getType());
    }

    @Test
    void createInternship_invalidTechStack_throwsException() {
        final String[] invalidTechStacks = {null, "", " ", "  "};
        for (String techStack : invalidTechStacks) {
            AddSoftwareCommand addSoftwareCommand = new AddSoftwareCommand();
            addSoftwareCommand.parameters.put("/c", "Google");
            addSoftwareCommand.parameters.put("/r", "Software Engineer");
            addSoftwareCommand.parameters.put("/tech", techStack);
            assertThrows(IllegalArgumentException.class, addSoftwareCommand::createInternship);
        }
    }
}
