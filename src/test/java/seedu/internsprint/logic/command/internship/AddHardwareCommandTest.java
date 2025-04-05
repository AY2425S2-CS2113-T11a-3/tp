package seedu.internsprint.logic.command.internship;

import seedu.internsprint.model.internship.HardwareInternship;
import seedu.internsprint.model.internship.Internship;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddHardwareCommandTest {

    AddHardwareInternshipCommand addHardwareCommand;
    HashMap<String, String> parameters;

    @BeforeEach
    void setUp() {
        addHardwareCommand = new AddHardwareInternshipCommand();
        parameters = addHardwareCommand.getParameters();
        parameters.put("/c", "Google");
        parameters.put("/r", "Hardware Engineer");
    }

    @Test
    void createInternship_validParameters_correctlyConstructed() {
        parameters.put("/hardtech", "C, C++");
        addHardwareCommand.setParameters(parameters);
        Internship internship = addHardwareCommand.createInternship();
        assertEquals("Google", internship.getCompanyName());
        assertEquals("Hardware Engineer", internship.getRole());
        assertEquals("C, C++", ((HardwareInternship) internship).getEmbeddedSystems());
        assertEquals("hardware", internship.getType());
    }

    @Test
    void createInternship_validOptionalParameters_correctlyConstructed() {
        parameters.put("/hardtech", "C, C++");
        parameters.put("/eli", "Eligibility");
        parameters.put("/desc", "Description");
        parameters.put("/status", "Status");
        parameters.put("/ex", "Expectations");
        addHardwareCommand.setParameters(parameters);

        Internship internship = addHardwareCommand.createInternship();
        assertEquals("Google", internship.getCompanyName());
        assertEquals("Hardware Engineer", internship.getRole());
        assertEquals("C, C++", ((HardwareInternship) internship).getEmbeddedSystems());
        assertEquals("Eligibility", internship.getEligibility());
        assertEquals("Description", internship.getDescription());
        assertEquals("Status", internship.getStatus());
        assertEquals("Expectations", internship.getExpectations());
    }

    @Test
    void createInternship_invalidEmbeddedSystems_throwsException() {
        final String[] invalidEmbeddedSystems = {null, "", " ", "  "};
        for (String embeddedSystems : invalidEmbeddedSystems) {
            parameters.put("/hardtech", embeddedSystems);
            addHardwareCommand.setParameters(parameters);
            assertThrows(IllegalArgumentException.class, addHardwareCommand::createInternship);
        }
    }
}
