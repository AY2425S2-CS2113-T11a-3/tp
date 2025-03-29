package seedu.internsprint.command;

import seedu.internsprint.internship.HardwareInternship;
import seedu.internsprint.internship.Internship;

import java.util.Set;
import java.util.logging.Level;

public class AddHardwareInternshipCommand extends AddInternshipCommand {
    public static final String COMMAND_WORD = "add hardware";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a hardware internship to the internship list.\n"
            + "    Parameters: " + "/c COMPANY_NAME " + "/r ROLE " + "/hardtech HARDWARE_TECHNOLOGIES \n"
            + "    Example: " + COMMAND_WORD + " /c Google " + "/r Hardware Engineer " + "/hardtech C, C++";
    public static final String[] REQUIRED_PARAMETERS = {"/c", "/r", "/hardtech"};
    public static final String[] OPTIONAL_PARAMETERS = {"/eli", "/ex", "/status","/desc"};

    public AddHardwareInternshipCommand() {
        super(Set.of(REQUIRED_PARAMETERS), Set.of(OPTIONAL_PARAMETERS));
    }

    @Override
    protected String getUsageMessage() {
        return MESSAGE_USAGE;
    }

    @Override
    protected Internship createInternship() {
        logger.log(Level.INFO, "Creating Hardware Internship");
        String companyName = parameters.get("/c");
        String role = parameters.get("/r");
        String embeddedSystems = parameters.get("/hardtech");
        String eligibility = parameters.get("/eli");  //this will return null if user doesn't provide the same
        String description = parameters.get("/desc");
        String status = parameters.get("/status");
        String expectations = parameters.get("/ex");

        return new HardwareInternship(
                companyName,
                role,
                embeddedSystems,
                eligibility != null ? eligibility : "",
                description != null ? description : "",
                status != null ? status : "",
                expectations != null ? expectations : ""
        );
    }
}
