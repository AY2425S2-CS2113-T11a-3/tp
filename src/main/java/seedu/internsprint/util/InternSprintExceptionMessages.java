package seedu.internsprint.util;

public class InternSprintExceptionMessages {
    public static final String ILLEGAL_VALUE_INPUT = "Illegal input: Unexpected '/' found in value for key";
    public static final String MISSING_VALUE_INPUT = "Invalid input: key %s found with no value.";
    public static final String MISSING_REQUIRED_PARAMETERS = "Missing required parameters: %s";

    public static final String UNABLE_TO_CREATE_DIRECTORY = "Unable to create directory: %s";
    public static final String FILE_ALREADY_EXISTS = "File already exists: %s";
    public static final String UNABLE_TO_CREATE_FILE = "Unable to create file: %s";
    public static final String UNABLE_TO_WRITE_FILE = "Unable to write to file: %s";

    public static final String EDIT_UNABLE_TO_FIND_INTERNSHIP = """
            This internship is not found within your saved list. Check that /index is provided with a valid
        index reference, or that the type of internship you are editing contains that type of flag""";
    public static final String EDIT_INVALID_PARAMS = "You have not entered a valid parameter to edit.\n"
            + "    The edit command works as mentioned below:";
    public static final String ILLEGAL_COMMAND_INPUT = "Illegal input: Unexpected '/' found in value for key";
    public static final String MISSING_COMMAND_INPUT = "Invalid input: key /%s found with no value.";
    public static final String HELP_INVALID_PARAMETERS = "Invalid parameters: Use 'help' to see a list of all " +
            "commands or 'help COMMAND' for specific command instructions.";

}
