package seedu.internsprint.command;

import seedu.internsprint.internship.InternshipList;

import java.util.HashMap;

public abstract class Command {
    /** Key-value pairs of arguments entered by user. */
    protected HashMap<String, String> parameters = new HashMap<>();

    public void setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    protected abstract boolean isValidParameters();

    public abstract CommandResult execute(InternshipList internships);
}
