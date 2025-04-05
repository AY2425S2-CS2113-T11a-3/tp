package seedu.internsprint.logic.command.internship;

import seedu.internsprint.logic.command.Command;
import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.internship.Internship;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;
import seedu.internsprint.util.InternSprintLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.internsprint.util.InternSprintExceptionMessages.LIST_INVALID_PARAMS;
import static seedu.internsprint.util.InternSprintMessages.LIST_MESSAGE_SUCCESS;
import static seedu.internsprint.util.InternSprintMessages.NO_INTERNSHIPS_FOUND;

public class ListCommand extends Command {
    /**
     * The command word that triggers the list command
     */
    public static final String COMMAND_WORD = "list";

    /**
     * Usage instructions of the list command for users
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists every saved internship in your list, arranged "
            + "by categories\n"
            + "    Parameters: None\n"
            + "    Example: " + COMMAND_WORD;
    private static final Logger logger = InternSprintLogger.getLogger();

    /**
     * Checks if the parameters provided to the lost command are valid.
     *
     * @return True if no parameters are provided, false otherwise.
     */
    @Override
    public String getCommandType() {
        return "internship";
    }

    @Override
    protected boolean isValidParameters() {
        logger.log(Level.INFO, "Entering into the check for parameters in list command");
        return parameters.isEmpty();
    }

    /**
     * Executes the command to list internships.
     *
     * @param internshipList InternshipList object.
     * @param user Userprofile object.
     * @return CommandResult object containing the result of the command execution.
     */
    @Override
    public CommandResult execute(InternshipList internshipList, UserProfile user) {
        logger.log(Level.INFO, "Executing list command");
        assert internshipList != null : "InternshipList should not be null";
        assert user != null : "UserProfile should not be null";
        assert internshipList.getInternshipMap() != null : "InternshipMap should not be null";

        CommandResult result;
        List<String> feedback = new ArrayList<>();
        int count = 1;

        if (!isValidParameters()) {
            logger.log(Level.WARNING, "There are invalid parameters so error result is output to user.");
            feedback.add(LIST_INVALID_PARAMS);
            feedback.add(MESSAGE_USAGE);
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            return result;
        }

        feedback.add(LIST_MESSAGE_SUCCESS);

        feedback.add("Software Internships:");
        assert internshipList.getInternshipMap().containsKey("software") : "Internship map should contain key " +
                "'software'";
        for (Internship everyInternship : internshipList.getInternshipMap().get("software")) {
            assert everyInternship != null : "A software internship entry should not be null";
            feedback.add("  " + count + ". " + everyInternship.toString());
            count++;
        }

        feedback.add("Hardware Internships:");
        assert internshipList.getInternshipMap().containsKey("hardware") : "Internship map should contain key " +
                "'hardware'";
        for (Internship everyInternship : internshipList.getInternshipMap().get("hardware")) {
            assert everyInternship != null : "A hardware internship entry should not be null";
            feedback.add("  " + count + ". " + everyInternship.toString());
            count++;
        }

        feedback.add("General Internships:");
        assert internshipList.getInternshipMap().containsKey("general") : "Internship map should contain key 'general'";
        for (Internship everyInternship : internshipList.getInternshipMap().get("general")) {
            assert everyInternship != null : "A general internship entry should not be null";
            feedback.add("  " + count + ". " + everyInternship.toString());
            count++;
        }

        if (count == 1) {
            result = new CommandResult(NO_INTERNSHIPS_FOUND);
            result.setSuccessful(true);
            logger.log(Level.INFO, "No internships found in the list");
            return result;
        }

        logger.log(Level.INFO, "List command executed successfully");
        result = new CommandResult(feedback);
        result.setSuccessful(true);
        return result;
    }
}

