package seedu.internsprint.command;

import seedu.internsprint.internship.HardwareInternship;
import seedu.internsprint.internship.Internship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddHardwareCommandTest {

    @Test
    void createInternship_validParameters_correctlyConstructed() {
        AddHardwareCommand addHardwareCommand = new AddHardwareCommand();
        addHardwareCommand.parameters.put("/c", "Google");
        addHardwareCommand.parameters.put("/r", "Hardware Engineer");
        addHardwareCommand.parameters.put("/tech", "C, C++");
        Internship internship = addHardwareCommand.createInternship();
        assertEquals("Google", internship.getCompanyName());
        assertEquals("Hardware Engineer", internship.getRole());
        assertEquals("C, C++", ((HardwareInternship) internship).getEmbeddedSystems());
        assertEquals("hardware", internship.getType());
    }

    @Test
    void createInternship_invalidEmbeddedSystems_throwsException() {
        final String[] invalidEmbeddedSystems = {null, "", " ", "  "};
        for (String embeddedSystems : invalidEmbeddedSystems) {
            AddHardwareCommand addHardwareCommand = new AddHardwareCommand();
            addHardwareCommand.parameters.put("/c", "Google");
            addHardwareCommand.parameters.put("/r", "Hardware Engineer");
            addHardwareCommand.parameters.put("/tech", embeddedSystems);
            assertThrows(IllegalArgumentException.class, addHardwareCommand::createInternship);
        }
    }
}
