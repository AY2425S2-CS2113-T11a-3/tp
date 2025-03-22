package seedu.internsprint.command;

import seedu.internsprint.handler.Parser;
import seedu.internsprint.internship.Internship;
import seedu.internsprint.internship.InternshipList;
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
    public static final String COMMAND_WORD = "desc";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the description of a particular internship.\n"
            + "    Parameter: " + "/index INDEX_OF_INTERNSHIP\n"
            + "    Example: " + COMMAND_WORD + " /index 1 ";
    public static final String[] REQUIRED_PARAMETERS = {"/index"};
    private static final Logger logger = InternSprintLogger.getLogger();

    @Override
    protected boolean isValidParameters() {
        logger.log(Level.INFO, "Entering check for parameters in description command.");
        return parameters.size() == REQUIRED_PARAMETERS.length
            && parameters.containsKey(REQUIRED_PARAMETERS[0]);
    }

    @Override
    public CommandResult execute(InternshipList internships) {
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
            validIndex = Parser.validateIndex(parameters.get("/index"), internships);
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Index for description command is out of range.", e);
            feedback.add(e.getMessage());
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            return result;
        }

        int index = Integer.parseInt(validIndex[1]);
        String type = validIndex[0];

        HashMap<String, ArrayList<Internship>> internshipMap = internships.getInternshipMap();
        Internship foundInternship = internshipMap.get(type).get(index);

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
