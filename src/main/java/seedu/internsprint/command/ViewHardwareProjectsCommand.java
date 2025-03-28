package seedu.internsprint.command;

import seedu.internsprint.internship.InternshipList;
import seedu.internsprint.project.Project;
import seedu.internsprint.userprofile.UserProfile;
import seedu.internsprint.util.InternSprintLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.internsprint.util.InternSprintMessages.PROJECTS_VIEW_SUCCESS_MESSAGE;

public class ViewHardwareProjectsCommand extends Command {
    private static final Logger logger = InternSprintLogger.getLogger();
    @Override
    protected boolean isValidParameters() {
        assert parameters.isEmpty():"There should be no flags in this command.";
        return true;
    }

    @Override
    public String getCommandType() {
        return "user";
    }

    /**
     * Showcases CV-formatted version of projects
     * @param user refers to user saved in session
     * @return formatted projects string or error message in CommandResult type
     */
    @Override
    public CommandResult execute(InternshipList internships, UserProfile user) {
        logger.log(Level.INFO, "Entering execute in view projects hadrware command...");
        CommandResult result;
        List<String> feedback = new ArrayList<>();
        feedback.add(PROJECTS_VIEW_SUCCESS_MESSAGE);
        for(Project gen:user.projects.getProjectMap().get("hardware")) {
            feedback.add(gen.toDescription());
        }
        result = new CommandResult(feedback);
        result.setSuccessful(true);
        return result;
    }
}
