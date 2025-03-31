package seedu.internsprint.logic.command.internship;

import seedu.internsprint.model.internship.Internship;
import seedu.internsprint.model.internship.SoftwareInternship;

import java.util.Set;
import java.util.logging.Level;

public class AddSoftwareInternshipCommand extends AddInternshipCommand {
    public static final String COMMAND_WORD = "add software";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a software internship to the internship list.\n"
            + "    Parameters: " + "/c COMPANY_NAME " + "/r ROLE " + "/tech TECHNOLOGIES\n"
            + "    Example: " + COMMAND_WORD + " /c Google " + "/r Software Engineer " + "/tech Java, Python";
    public static final String[] REQUIRED_PARAMETERS = {"/c", "/r", "/tech"};
    public static final String[] OPTIONAL_PARAMETERS = {"/eli", "/ex", "/status", "/desc"};

    public AddSoftwareInternshipCommand() {
        super(Set.of(REQUIRED_PARAMETERS), Set.of(OPTIONAL_PARAMETERS));
    }

    @Override
    protected String getUsageMessage() {
        return MESSAGE_USAGE;
    }

    @Override
    protected Internship createInternship() {
        logger.log(Level.INFO, "Creating Software Internship");
        String companyName = parameters.get("/c");
        String role = parameters.get("/r");
        String techstack = parameters.get("/tech");
        String eligibility = parameters.get("/eli");  //this will return null if user doesn't provide the same
        String description = parameters.get("/desc");
        String status = parameters.get("/status");
        String expectations = parameters.get("/ex");

        return new SoftwareInternship(
                companyName,
                role,
                techstack,
                eligibility != null ? eligibility : "",
                description != null ? description : "",
                status != null ? status : "",
                expectations != null ? expectations : ""
        );
    }
}
