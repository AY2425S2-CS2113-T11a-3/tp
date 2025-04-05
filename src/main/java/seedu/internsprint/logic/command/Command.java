package seedu.internsprint.logic.command;

import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;

import java.util.HashMap;

/**
 * Represents a command to be executed.
 */
public abstract class Command {
    /**
     * Key-value pairs of arguments entered by user.
     */
    protected HashMap<String, String> parameters = new HashMap<>();

    public void setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    /**
     * Checks if the parameters entered by the user are valid.
     *
     * @return True if the parameters are valid, false otherwise.
     */
    protected abstract boolean isValidParameters();

    /**
     * Executes the command.
     *
     * @param internships InternshipList or UserProfile user.
     * @param user Userprofile object.
     * @return CommandResult object.
     */
    public abstract CommandResult execute(InternshipList internships, UserProfile user);

    public abstract String getCommandType();
}
