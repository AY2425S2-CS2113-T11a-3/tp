package seedu.internsprint.command;

import seedu.internsprint.internship.InternshipList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.internsprint.util.InternSprintExceptionMessages.USERPROFILE_INVALID_PARAMS;

public class UserProfileCommand extends Command{
    public static final String COMMAND_WORD = "my";
    public static final String MESSAGE_USAGE = "    " + COMMAND_WORD + ": Edits your user profile to save details about"
            + "yourself.\n"+ "     Parameters: " + "/c COMPANIES_YOU_PREFER /r ROLES_YOU_PREFER /ygoals YEARLY_GOALS"
            + " /mgoals MONTHLY_GOALS\n" + "     /pay PAY_RANGE /ind INDUSTRIES_YOU_PREFER /time TIME_RANGE " +
            "/name YOUR_NAME\n"
            + "     Example: " + COMMAND_WORD + " /name John Doe /c Google,Java /r Hardware Engineer, Automation Intern"
            + " /pay 2000-3000";
    public static final String[] OPTIONAL_PARAMETERS = {"/pay", "/ind", "/time", "/name",
                                                        "/ygoals", "/mgoals", "/c", "/r"};
    @Override
    protected boolean isValidParameters() {
        for (String key : parameters.keySet()) {
            if (!Arrays.asList(OPTIONAL_PARAMETERS).contains(key)) {
                System.out.println("Invalid key found: " + key);
                return false;
            }
        }
        return true;
    }

    @Override
    public CommandResult execute(InternshipList internships) {
        CommandResult result;
        List<String> feedback = new ArrayList<>();
        if (!isValidParameters()) {
            feedback.add(USERPROFILE_INVALID_PARAMS);
            feedback.add(MESSAGE_USAGE);
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            return result;
        }
        return null;
    }
}
