package seedu.internsprint.command;

import seedu.internsprint.handler.CommandParser;
import seedu.internsprint.project.Project;
import seedu.internsprint.project.SoftwareProject;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;

public class ProjectSoftwareCommand extends ProjectCommand {
    public static final String COMMAND_WORD = "project software";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a software project to your user profile.\n"
            + "    Parameters: " + "/n PROJECT_NAME " + "/r ROLE " + "/pro PROGRAMMING_LANGUAGES /obj OBJECTIVES"
            + " /desc DESCRIPTION /dur DURATION\n"
            + "    Example: " + COMMAND_WORD + " /n Team Project for CS2113 " + "/r Unit Tester " +
            "/pro Java, C++" + "/obj To get an A+ /desc Worked at identifying feature flaws in app"
            +" /dur May-August";
    public static final String[] REQUIRED_PARAMETERS = {"/n", "/r", "/pro","/obj","/desc","/dur"};

    public ProjectSoftwareCommand() {
        super(Set.of(REQUIRED_PARAMETERS));
    }

    @Override
    protected String getUsageMessage() {
        return MESSAGE_USAGE;
    }


    @Override
    protected Project createProject() {
        logger.log(Level.INFO, "Creating Software Project");
        String projectName = parameters.get("/n");
        String role = parameters.get("/r");
        String programmingLanguagesString = parameters.get("/pro");
        List<String > programmingLanguages = CommandParser.splitToWords(programmingLanguagesString);
        String objectives = parameters.get("/obj");
        String description = parameters.get("/desc");
        String duration = parameters.get("/dur");

        return new SoftwareProject(
                projectName,
                role,
                programmingLanguages,
                objectives,
                description,
                duration
        );
    }

}
