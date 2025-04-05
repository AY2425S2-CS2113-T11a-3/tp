package seedu.internsprint.logic.command.internship;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddInterviewCommandTest {

    @Test
    void isValidParameters_provideCorrectParameters_returnsTrue() {
        AddInterviewCommand addInterviewCommand = new AddInterviewCommand();
        HashMap<String, String> parameters = addInterviewCommand.getParameters();
        parameters.put("/index", "1");
        parameters.put("/date", "2023-10-10");
        parameters.put("/start", "10:00");
        parameters.put("/end", "11:00");
        parameters.put("/type", "Coding");
        addInterviewCommand.setParameters(parameters);
        assertTrue(addInterviewCommand.isValidParameters());
    }

    @Test
    void isValidParameters_provideNoIndex_returnsFalse() {
        AddInterviewCommand addInterviewCommand = new AddInterviewCommand();
        HashMap<String, String> parameters = addInterviewCommand.getParameters();
        parameters.put("/date", "2023-10-10");
        parameters.put("/start", "10:00");
        parameters.put("/end", "11:00");
        parameters.put("/type", "Coding");
        addInterviewCommand.setParameters(parameters);
        assertFalse(addInterviewCommand.isValidParameters());
    }

    @Test
    void isValidParameters_provideNoDate_returnsFalse() {
        AddInterviewCommand addInterviewCommand = new AddInterviewCommand();
        HashMap<String, String> parameters = addInterviewCommand.getParameters();
        parameters.put("/index", "1");
        parameters.put("/start", "10:00");
        parameters.put("/end", "11:00");
        parameters.put("/type", "Coding");
        addInterviewCommand.setParameters(parameters);
        assertFalse(addInterviewCommand.isValidParameters());
    }
}
