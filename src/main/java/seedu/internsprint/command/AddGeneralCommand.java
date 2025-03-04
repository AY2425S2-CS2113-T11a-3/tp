package seedu.internsprint.command;

import seedu.internsprint.internship.GeneralInternship;
import seedu.internsprint.internship.Internship;

import java.util.Set;

public class AddGeneralCommand extends AddCommand {
    public static final String COMMAND_WORD = "add general";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a general internship to the internship list.\n"
            + "     Parameters: " + "/c COMPANY_NAME " + "/r ROLE " + "/dept DEPARTMENT\n"
            + "     Example: " + COMMAND_WORD + " /c Google " + "/r Human Resource " + "/dept HR";
    public static final String[] REQUIRED_PARAMETERS = {"/c", "/r", "/dept"};

    public AddGeneralCommand() {
        super(Set.of(REQUIRED_PARAMETERS));
    }

    @Override
    protected String getUsageMessage() {
        return MESSAGE_USAGE;
    }

    @Override
    protected Internship createInternship() {
        return new GeneralInternship(parameters.get("/c"), parameters.get("/r"), parameters.get("/dept"));
    }
}
