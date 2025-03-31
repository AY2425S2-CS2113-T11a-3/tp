package seedu.internsprint.logic.command.internship;

import org.junit.jupiter.api.Test;

import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;
import seedu.internsprint.util.InternSprintExceptionMessages;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HelpCommandTest {
    @Test
    public void helpCommand_provideEmptyParameter_returnsValid() {
        HelpCommand helpCommand = new HelpCommand();
        CommandResult result = helpCommand.execute(new InternshipList(), new UserProfile());
        assertTrue(result.isSuccessful());
    }

    @Test
    public void helpCommand_provideCommandParameter_returnsValid() {
        HelpCommand helpCommand = new HelpCommand();
        HashMap<String, String> parameters = helpCommand.getParameters();
        parameters.put("command", "add general");
        helpCommand.setParameters(parameters);
        CommandResult result = helpCommand.execute(new InternshipList(), new UserProfile());
        assertTrue(result.isSuccessful());
        assertEquals("-> "+ AddGeneralInternshipCommand.MESSAGE_USAGE, result.getFeedbackToUser().get(0));
    }

    @Test
    public void helpCommand_provideInvalidCommand_returnsInvalid() {
        HelpCommand helpCommand = new HelpCommand();
        HashMap<String, String> parameters = helpCommand.getParameters();
        parameters.put("command", "randomCommand");
        helpCommand.setParameters(parameters);
        CommandResult result = helpCommand.execute(new InternshipList(), new UserProfile());
        assertFalse(result.isSuccessful());
        assertEquals(InternSprintExceptionMessages.HELP_INVALID_PARAMETERS, result.getFeedbackToUser().get(0));
    }
}
