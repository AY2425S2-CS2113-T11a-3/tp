package seedu.internsprint.logic.command;

import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;
import seedu.internsprint.util.InternSprintLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.internsprint.util.InternSprintMessages.BYE_MESSAGE;
import static seedu.internsprint.util.InternSprintMessages.SAVE_SUCCESS_MESSAGE;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "    Parameters: None\n"
            + "    Example: " + COMMAND_WORD;
    private static Logger logger = InternSprintLogger.getLogger();

    @Override
    protected boolean isValidParameters() {
        return true;
    }

    @Override
    public String getCommandType() {
        return "internship";
    }

    @Override
    public CommandResult execute(InternshipList internships, UserProfile user) {
        CommandResult result;
        List<String> feedback = new ArrayList<>();

        try {
            internships.saveInternships();
            feedback.add(SAVE_SUCCESS_MESSAGE);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error saving internships while exiting");
            feedback.add(e.getMessage());
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            return result;
        }

        feedback.add(BYE_MESSAGE);
        result = new CommandResult(feedback);
        result.setSuccessful(true);
        result.setExit(true);
        return result;
    }
}
