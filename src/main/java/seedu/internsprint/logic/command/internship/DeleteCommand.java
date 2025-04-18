package seedu.internsprint.logic.command.internship;

import seedu.internsprint.logic.command.Command;
import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.logic.parser.CommandParser;
import seedu.internsprint.model.internship.Internship;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;
import seedu.internsprint.util.InternSprintLogger;
import seedu.internsprint.util.InternSprintMessages;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;
import java.util.List;

import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_INDEX;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an internship " +
            "based on its category and index.\n"
            + "    Parameters: " + "/index INDEX_OF_INTERNSHIP \n"
            + "    Example: " + COMMAND_WORD + " /index 2";
    public static final String[] REQUIRED_PARAMETERS = {"/index"};
    private static Logger logger = InternSprintLogger.getLogger();

    /**
     * Validates that the required parameters are present for this command.
     *
     * @return true if the index parameter is present, false otherwise.
     */
    @Override
    protected boolean isValidParameters() {
        return parameters.size() == REQUIRED_PARAMETERS.length
                && parameters.containsKey(REQUIRED_PARAMETERS[0]);
    }
    @Override
    public String getCommandType(){
        return "internship";
    }

    /**
     * Executes the delete command.
     * It deletes the internship entry at the specified index
     * from the relevant category, and saves the updated list.
     *
     * @param internships InternshipList
     * @param user UserProfile
     * @return CommandResult object indicating whether execution was successful or not
     */
    @Override
    public CommandResult execute(InternshipList internships, UserProfile user) {
        assert internships != null : "InternshipList should not be null";
        CommandResult result;
        List<String> feedback = new ArrayList<>();
        logger.log(Level.INFO, "Starting Delete Command processing");
        logger.log(Level.INFO, "Parameters in DeleteCommand: " + parameters.toString());
        assert parameters != null : "parameters should not be null";

        if (!isValidParameters()) {
            feedback.add(MISSING_INDEX);
            feedback.add(MESSAGE_USAGE);
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            logger.log(Level.WARNING, "Missing index processing error");
            return result;
        }

        String[] validIndex;
        try {
            validIndex = CommandParser.validateIndex(parameters.get("/index"), internships);
            assert validIndex.length == 2 : "Parser.validateIndex should return a valid type and index";
        } catch (IllegalArgumentException e) {
            feedback.add(e.getMessage());
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            logger.log(Level.WARNING, "Invalid index processing error");
            return result;
        }

        int index = Integer.parseInt(validIndex[1]);
        String type = validIndex[0];

        Internship internshipToDelete = internships.getInternshipMap().get(type).get(index);
        assert internshipToDelete != null : "Internship to delete should not be null";

        internships.deleteInternship(type, index);

        try {
            internships.saveInternships();
        } catch (IOException e) {
            feedback.add(e.getMessage());
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            logger.log(Level.WARNING, "Save processing error");
            return result;
        }

        feedback.add(String.format(InternSprintMessages.SUCCESSFUL_DELETE, internshipToDelete));
        result = new CommandResult(feedback);
        result.setSuccessful(true);
        logger.log(Level.INFO, "End of Delete Command processing");
        return result;
    }
}


