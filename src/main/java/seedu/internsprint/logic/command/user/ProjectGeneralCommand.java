package seedu.internsprint.logic.command.user;

import seedu.internsprint.model.userprofile.project.GeneralProject;
import seedu.internsprint.model.userprofile.project.Project;

import java.util.Set;
import java.util.logging.Level;

public class ProjectGeneralCommand extends ProjectCommand {
    public static final String COMMAND_WORD = "project general";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a general project to your user profile.\n"
            + "    Parameters: " + "/n PROJECT_NAME " + "/r ROLE " + "/dept DEPARTMENT /obj OBJECTIVES"
            + " /desc DESCRIPTION /dur DURATION\n"
            + "    Example: " + COMMAND_WORD + " /n Team Project for CS2113 " + "/r Unit Tester " +
            "/dept Software Engineering" + "/obj To get an A+ /desc Worked at identifying feature flaws in app"
            +" /dur May-August";
    public static final String[] REQUIRED_PARAMETERS = {"/n", "/r", "/dept","/obj","/desc","/dur"};

    public ProjectGeneralCommand() {
        super(Set.of(REQUIRED_PARAMETERS));
    }

    @Override
    protected String getUsageMessage() {
        return MESSAGE_USAGE;
    }


    @Override
    protected Project createProject() {
        logger.log(Level.INFO, "Creating General Project");
        String projectName = parameters.get("/n");
        String role = parameters.get("/r");
        String department = parameters.get("/dept");
        String objectives = parameters.get("/obj");  //this will return null if user doesn't provide the same
        String description = parameters.get("/desc");
        String duration = parameters.get("/dur");

        return new GeneralProject(
                projectName,
                role,
                department,
                objectives,
                description,
                duration
        );
    }

}
