package seedu.internsprint.command;

import seedu.internsprint.internship.InternshipList;

import java.util.ArrayList;
import java.util.List;

import static seedu.internsprint.util.InternSprintMessages.BYE_MESSAGE;
import static seedu.internsprint.util.InternSprintMessages.SAVE_SUCCESS_MESSAGE;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "Parameters: None\n"
            + "Example: " + COMMAND_WORD;

    @Override
    protected boolean isValidParameters() {
        return true;
    }

    @Override
    public CommandResult execute(InternshipList internships) {
        CommandResult result;
        List<String> feedback = new ArrayList<>();

        try {
            internships.saveInternships();
            feedback.add(SAVE_SUCCESS_MESSAGE);
        } catch (Exception e) {
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
