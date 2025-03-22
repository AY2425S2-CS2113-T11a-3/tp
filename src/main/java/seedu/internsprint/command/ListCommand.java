package seedu.internsprint.command;

import seedu.internsprint.internship.Internship;
import seedu.internsprint.internship.InternshipList;
import seedu.internsprint.util.InternSprintLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.internsprint.util.InternSprintMessages.LIST_MESSAGE_SUCCESS;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists every saved internship in your list, arranged "
            + "by categories\n"
            + "    Parameters: None\n"
            + "    Example: " + COMMAND_WORD;
    private static final Logger logger = InternSprintLogger.getLogger();

    @Override
    protected boolean isValidParameters() {
        logger.log(Level.INFO, "Entering into the check for parameters in list command");
        return parameters.isEmpty();
    }

    @Override
    public CommandResult execute(InternshipList internshipList) {
        logger.log(Level.INFO, "Executing list command");
        CommandResult result;
        List<String> feedback = new ArrayList<>();

        int count = 1;
        feedback.add(LIST_MESSAGE_SUCCESS);

        feedback.add("Software Internships:");
        for (Internship everyInternship : internshipList.getInternshipMap().get("software")) {
            feedback.add("  " + count + ". " + everyInternship.toString());
            count++;
        }

        feedback.add("Hardware Internships:");
        for (Internship everyInternship : internshipList.getInternshipMap().get("hardware")) {
            feedback.add("  " + count + ". " + everyInternship.toString());
            count++;
        }

        feedback.add("General Internships:");
        for (Internship everyInternship : internshipList.getInternshipMap().get("general")) {
            feedback.add("  " + count + ". " + everyInternship.toString());
            count++;
        }

        logger.log(Level.INFO, "Internships listed successfully");
        result = new CommandResult(feedback);
        result.setSuccessful(true);
        return result;

    }
}

