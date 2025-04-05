package seedu.internsprint.logic.command.user;

import seedu.internsprint.logic.command.Command;
import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;
import seedu.internsprint.util.InternSprintLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.internsprint.util.InternSprintMessages.USER_VIEW_SUCCESS_MESSAGE;

public class ViewUserCommand extends Command {
    public static final String COMMAND_WORD = "view user";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays the user profile " +
            "details in a Resume format.\n"
            + "    Parameters: None\n"
            + "    Example: " + COMMAND_WORD;
    private static final Logger logger = InternSprintLogger.getLogger();

    @Override
    protected boolean isValidParameters() {
        assert parameters.isEmpty():"There should be no flags in this command.";
        return true;
    }

    @Override
    public String getCommandType() {
        return "user";
    }

    /**
     * Showcases CV-formatted version of user profile
     *
     * @param internships refers to the internship list
     * @param user refers to user saved in session
     * @return formatted profile string or error message in CommandResult type
     */
    @Override
    public CommandResult execute(InternshipList internships, UserProfile user) {
        logger.log(Level.INFO, "Entering execute in view user profile command...");
        CommandResult result;
        List<String> feedback = new ArrayList<>();
        feedback.add(USER_VIEW_SUCCESS_MESSAGE);
        feedback.add(user.toExtendedString());
        result = new CommandResult(feedback);
        result.setSuccessful(true);
        return result;
    }
}
