package seedu.internsprint.command;

import seedu.internsprint.internship.Internship;
import seedu.internsprint.internship.SoftwareInternship;

import java.util.Set;

public class AddSoftwareCommand extends AddCommand {
    public static final String COMMAND_WORD = "add software";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a software internship to the internship list.\n"
            + "     Parameters: " + "/c COMPANY_NAME " + "/r ROLE " + "/tech TECHNOLOGIES\n"
            + "     Example: " + COMMAND_WORD + " /c Google " + "/r Software Engineer " + "/tech Java, Python";
    public static final String[] REQUIRED_PARAMETERS = {"/c", "/r", "/tech"};

    public AddSoftwareCommand() {
        super(Set.of(REQUIRED_PARAMETERS));
    }

    @Override
    protected String getUsageMessage() {
        return MESSAGE_USAGE;
    }

    @Override
    protected Internship createInternship() {
        return new SoftwareInternship(parameters.get("/c"), parameters.get("/r"), parameters.get("/tech"));
    }
}
