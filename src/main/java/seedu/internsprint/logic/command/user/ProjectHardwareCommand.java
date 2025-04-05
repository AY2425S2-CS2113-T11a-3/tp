package seedu.internsprint.logic.command.user;

import seedu.internsprint.logic.parser.CommandParser;
import seedu.internsprint.model.userprofile.project.HardwareProject;
import seedu.internsprint.model.userprofile.project.Project;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;

public class ProjectHardwareCommand extends ProjectCommand {
    public static final String COMMAND_WORD = "project hardware";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a hardware project to your user profile.\n"
            + "    Parameters: " + "/n PROJECT_NAME " + "/r ROLE " + "/hcomp HARDWARE_COMPONENTS /obj OBJECTIVES"
            + " /desc DESCRIPTION /dur DURATION\n"
            + "    Example: " + COMMAND_WORD + " /n Team Project for EE2026 " + "/r Ui Developer " +
            "/hcomp Basys Board" + "/obj To get an A+ /desc Worked at creating pixel art for the UI"
            +" /dur May-August";
    public static final String[] REQUIRED_PARAMETERS = {"/n", "/r", "/hcomp","/obj","/desc","/dur"};

    public ProjectHardwareCommand() {
        super(Set.of(REQUIRED_PARAMETERS));
    }

    @Override
    protected String getUsageMessage() {
        return MESSAGE_USAGE;
    }


    @Override
    protected Project createProject() {
        logger.log(Level.INFO, "Creating Hardware Project");
        String projectName = parameters.get("/n");
        String role = parameters.get("/r");
        String hardwareComponentsString = parameters.get("/hcomp");
        List<String > hardwareComponents = CommandParser.splitToWords(hardwareComponentsString);
        String objectives = parameters.get("/obj");
        String description = parameters.get("/desc");
        String duration = parameters.get("/dur");

        return new HardwareProject(
                projectName,
                role,
                hardwareComponents,
                objectives,
                description,
                duration
        );
    }

}
