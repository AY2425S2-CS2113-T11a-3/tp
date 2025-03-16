package seedu.internsprint.util;

public class InternSprintExceptionMessages {
    public static final String ILLEGAL_VALUE_INPUT = "Illegal input: Unexpected '/' found in value for key";
    public static final String MISSING_VALUE_INPUT = "Invalid input: key %s found with no value.";
    public static final String MISSING_REQUIRED_PARAMETERS = "Missing required parameters: %s";

    public static final String UNABLE_TO_CREATE_DIRECTORY = "Unable to create directory: %s";
    public static final String FILE_ALREADY_EXISTS = "File already exists: %s";
    public static final String UNABLE_TO_CREATE_FILE = "Unable to create file: %s";
    public static final String UNABLE_TO_WRITE_FILE = "Unable to write to file: %s";
    public static final String UNABLE_TO_READ_FILE = "Unable to read file: %s";

    public static final String EDIT_UNABLE_TO_FIND_INTERNSHIP = """
            This internship is not found within your saved list. Check that /index is provided with a valid
        index reference, or that the type of internship you are editing contains that type of flag""";
    public static final String EDIT_INVALID_PARAMS = "You have not entered a valid parameter to edit.\n"
            + "    The edit command works as mentioned below:";
    public static final String USERPROFILE_INVALID_PARAMS = "You have not entered a valid parameter to user profile.\n"
            + "    The user profile command works as mentioned below:";
    public static final String INVALID_CATEGORY_ERROR = "Invalid category: Valid categories are: " +
            "software, hardware, general.";
    public static final String INVALID_INDEX_ERROR = "Invalid index: Please enter a valid index within the list.";
}
