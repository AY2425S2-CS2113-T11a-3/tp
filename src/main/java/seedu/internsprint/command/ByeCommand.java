package seedu.internsprint.command;

import seedu.internsprint.internship.InternshipList;

import static seedu.internsprint.util.InternSprintMessages.BYE_MESSAGE;

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
        CommandResult result = new CommandResult(BYE_MESSAGE);
        result.setSuccessful(true);
        result.setExit(true);
        return result;
    }
}
