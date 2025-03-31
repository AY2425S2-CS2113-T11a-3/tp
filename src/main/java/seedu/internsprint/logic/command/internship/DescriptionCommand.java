package seedu.internsprint.logic.command.internship;

import seedu.internsprint.logic.command.Command;
import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.logic.parser.CommandParser;
import seedu.internsprint.model.internship.Internship;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;
import seedu.internsprint.util.InternSprintLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.internsprint.util.InternSprintExceptionMessages.DESC_INVALID_PARAMS;
import static seedu.internsprint.util.InternSprintExceptionMessages.DESC_UNABLE_TO_FIND_INTERNSHIP;
import static seedu.internsprint.util.InternSprintMessages.DESC_MESSAGE_SUCCESS;

public class DescriptionCommand extends Command {
    /** The command word to trigger the description command. */
    public static final String COMMAND_WORD = "desc";

    /** Usage instructions of the description command for users **/
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the description of a particular internship.\n"
            + "    Parameter: " + "/index INDEX_OF_INTERNSHIP\n"
            + "    Example: " + COMMAND_WORD + " /index 1 ";

    /** Required parameter keys for the description command. */
    public static final String[] REQUIRED_PARAMETERS = {"/index"};
    private static final Logger logger = InternSprintLogger.getLogger();

    /**
     * Checks whether the parameters provided for the description command are valid.
     * This method ensures the parameters map contains exactly the required parameters
     * specifically the "/index" parameter.
     *
     * @return true if the parameters are valid, false otherwise
     */
    @Override
    protected boolean isValidParameters() {
        logger.log(Level.INFO, "Entering check for parameters in description command.");
        return parameters.size() == REQUIRED_PARAMETERS.length
                && parameters.containsKey(REQUIRED_PARAMETERS[0]);
    }


    @Override
    public String getCommandType() {
        return "internship";
    }
    /**
     * Executes the description command.
     *
     * @param internships InternshipList object.
     * @return CommandResult object containing the result of the command execution.
     */
    @Override
    public CommandResult execute(InternshipList internships, UserProfile user) {
        logger.log(Level.INFO, "Executing description command.");
        CommandResult result;
        List<String> feedback = new ArrayList<>();

        if (!isValidParameters()) {
            logger.log(Level.WARNING, "Invalid parameters in description command.");
            feedback.add(DESC_INVALID_PARAMS);
            feedback.add(MESSAGE_USAGE);
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            return result;
        }

        String[] validIndex;
        try {
            validIndex = CommandParser.validateIndex(parameters.get("/index"), internships);
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Index for description command is out of range.", e);
            feedback.add(e.getMessage());
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            return result;
        }

        int index = Integer.parseInt(validIndex[1]);
        String internshipType = validIndex[0];

        HashMap<String, ArrayList<Internship>> internshipMap = internships.getInternshipMap();

        Internship foundInternship = internshipMap.get(internshipType).get(index);

        if (foundInternship == null) {
            logger.log(Level.WARNING, "Internship not found.");
            result = new CommandResult(DESC_UNABLE_TO_FIND_INTERNSHIP);
            result.setSuccessful(false);
            return result;
        }

        logger.log(Level.INFO, "Description of internship successfully shown to user");
        feedback.add(DESC_MESSAGE_SUCCESS);
        feedback.addAll(foundInternship.toDescription());
        result = new CommandResult(feedback);
        result.setSuccessful(true);
        return result;
    }
}
