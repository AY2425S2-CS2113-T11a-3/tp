package seedu.internsprint.util;

public class InternSprintMessages {
    public static final String ADD_MESSAGE_SUCCESS = "New internship added";
    public static final String EDIT_MESSAGE_SUCCESS = "Internship edited successfully.";
    public static final String EDIT_MESSAGE_FAILURE = "Internship NOT edited successfully.";

    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship already exists in the internship list";
    public static final String BYE_MESSAGE = "Goodbye! Hope to see you again soon!";
    //the following logo is to be replaced with ASCII art after seeking approval
    public static final String LOGO = "InternSprint";
    public static final String WELCOME_MESSAGE =
        "Welcome to InternSprint! Your one-stop platform to manage and track your internship applications.\n" +
            "Get started by adding an internship! " +
            "InternSprint allows you to add three types of internships:\n" +
            "- General\n" +
            "- Software\n" +
            "- Hardware\n\n" +
            "To begin, use one of the following commands to learn the correct syntax:\n" +
            "  add general\n" +
            "  add software\n" +
            "  add hardware";

    public static final String SAVE_SUCCESS_MESSAGE = "Internships saved successfully\n" +
        "    You can view the list of internships at data/internships.txt";

    public static final String LIST_COUNT_MESSAGE = "Now you have %d internships in the list";
}
