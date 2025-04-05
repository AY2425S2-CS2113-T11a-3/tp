package seedu.internsprint.util;

public class InternSprintExceptionMessages {
    public static final String INVALID_COMMAND_TYPE = "Unknown command type: %s";
    public static final String ILLEGAL_VALUE_INPUT = "Illegal input: Unexpected '/' found in value for key";
    public static final String MISSING_VALUE_INPUT = "Invalid input: key %s found with no value.";
    public static final String MISSING_REQUIRED_PARAMETERS = "Missing required parameters: %s";
    public static final String REPEATED_FLAG = "Repeated flag found: %s";

    public static final String INVALID_DATE_FORMAT = "Invalid date format or no date found";
    public static final String PARTIAL_DATE_FORMAT = "Some input words is not recognized as a date/time. \n"
        + "    Date/Time interpreted from %s is: %s. Please give a better format.";
    public static final String END_TIME_BEFORE_START_TIME = "Interview start time cannot be after end time.";

    public static final String UNABLE_TO_CREATE_DIRECTORY = "Unable to create directory: %s";
    public static final String FILE_ALREADY_EXISTS = "File already exists: %s";
    public static final String UNABLE_TO_CREATE_FILE = "Unable to create file: %s";
    public static final String UNABLE_TO_WRITE_FILE = "Unable to write to file: %s.\n" +
        "    Please check if the file is open in another program or the program will continue without saving.";
    public static final String UNABLE_TO_READ_FILE = "Unable to read file: %s";

    public static final String EDIT_UNABLE_TO_FIND_INTERNSHIP = """
           You have entered the wrong flag for this type of internship.""";
    public static final String EDIT_INTERNSHIP_TYPE_TO_FLAG =
            "Note that hardware type of internships accepts the /hardtech flag and software internships accepts" +
         " the /tech flag.\n"
           + "    Use list command to check which category the internship you want to edit falls under.";
    public static final String DESC_UNABLE_TO_FIND_INTERNSHIP = "This internship is not found within your saved list." +
            " Check that /index is provided with a valid index reference.";
    public static final String EDIT_INVALID_PARAMS = "You have not entered a valid parameter to edit. Your flags are " +
            "not within specified flags used in edit command.\n"
            + "    The edit command works as mentioned below:";
    public static final String EDIT_NO_INDEX = "You have not entered the /index flag. \n"
            + "    The edit command works as mentioned below:";
    public static final String LIST_INVALID_PARAMS = "You have entered invalid parameters to the list command.\n"
            + "    The list command works as mentioned below:";
    public static final String SORT_INTERVIEWS_INVALID_PARAMS = "You have entered invalid parameters to the sort " +
            "command.\n The sortInterviews command works as mentioned below:";
    public static final String USERPROFILE_INVALID_PARAMS = "You have not entered a valid parameter to user profile.\n"
            + "    The user profile command works as mentioned below:";
    public static final String INVALID_CATEGORY_ERROR = "Invalid type provided: Valid types are: " +
            "software, hardware, general.";
    public static final String INVALID_INDEX_ERROR = "Invalid index: Please enter a valid index within the list.";
    public static final String ADD_INTERVIEW_INVALID_PARAMS = "You have not entered a valid parameter.\n"
            + "    The addition of an interview command works as mentioned below:";
    public static final String MISSING_INDEX = "Missing index: Please enter an index to reference the internship.";
    public static final String INVALID_INDEX_RANGE = "Invalid index range: Please enter a valid index within the list.";
    public static final String INVALID_INDEX = "Invalid index: Please enter a valid number as index.";
    public static final String HELP_INVALID_PARAMETERS = "Invalid parameters: Use 'help' to see a list of all " +
            "commands or 'help COMMAND' for specific command instructions.";
    public static final String DESC_INVALID_PARAMS = "You have not entered a valid index to show the description.\n"
            + "The desc command works as mentioned below:";
    public static final String DUPLICATE_INTERNSHIP = "This internship already exists in your list.";
    public static final String DUPLICATE_INTERVIEW = "An interview with the same date and time already exists for " +
        "that internship.\n" + "    Please check your date and time or the internship you are referring to.";
    public static final String UNABLE_TO_SAVE_PROFILE = "Unable to save user profile to file %s";
    public static final String UNABLE_TO_LOAD_PROFILE = "Unable to read user profile file";
}
