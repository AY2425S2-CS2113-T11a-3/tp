package seedu.internsprint.command;

import seedu.internsprint.internship.GeneralInternship;
import seedu.internsprint.internship.Internship;

import java.util.Set;

public class AddGeneralCommand extends AddCommand {
    public static final String COMMAND_WORD = "add general";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a general internship to the internship list.\n"
            + "    Parameters: " + "/c COMPANY_NAME " + "/r ROLE " + "/dept DEPARTMENT\n"
            + "    Example: " + COMMAND_WORD + " /c Google " + "/r Human Resource " + "/dept HR";
    public static final String[] REQUIRED_PARAMETERS = {"/c", "/r", "/dept"};
    public static final String[] OPTIONAL_PARAMETERS = {"/eli", "/ex", "/status","/desc"};

    public AddGeneralCommand() {
        super(Set.of(REQUIRED_PARAMETERS),Set.of(OPTIONAL_PARAMETERS));
    }

    @Override
    protected String getUsageMessage() {
        return MESSAGE_USAGE;
    }

    @Override
    protected Internship createInternship() {
        String companyName = parameters.get("/c");
        String role = parameters.get("/r");
        String department = parameters.get("/dept");
        String eligibility = parameters.get("/eli");  //this will return null if user doesn't provide the same
        String description = parameters.get("/desc");
        String status = parameters.get("/status");
        String expectations = parameters.get("/ex");

        return new GeneralInternship(
                companyName,
                role,
                department,
                eligibility != null ? eligibility : "",
                description != null ? description : "",
                status != null ? status : "",
                expectations != null ? expectations : ""
        );
    }

}
