package seedu.internsprint.logic.command.user;

import seedu.internsprint.logic.command.Command;
import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;
import seedu.internsprint.util.InternSprintLogger;

import java.util.ArrayList;
import java.util.Arrays;

import static seedu.internsprint.util.InternSprintExceptionMessages.USERPROFILE_INVALID_PARAMS;
import static seedu.internsprint.util.InternSprintMessages.USER_UPDATE_SUCCESS_MESSAGE;
import static seedu.internsprint.util.InternSprintExceptionMessages.INVALID_STIPEND_RANGE;
import static seedu.internsprint.util.InternSprintExceptionMessages.NOTE_NO_PARAMETERS;


import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Allows user to update or add fields to their saved user profile
 */
public class UserProfileCommand extends Command {
    public static final String COMMAND_WORD = "my";
    public static final String MESSAGE_USAGE =  COMMAND_WORD + ": Edits your user profile to save details about"
            + " yourself.\n" + "     Parameters: " + "/c COMPANIES_YOU_PREFER /r ROLES_YOU_PREFER /ygoals YEARLY_GOALS"
            + " /mgoals MONTHLY_GOALS\n" + "     /pay MIN_PAY_MAX_PAY /ind INDUSTRIES_YOU_PREFER /time TIME_RANGE " +
            "/name YOUR_NAME\n"
            + "     Example: " + COMMAND_WORD + " /name John Doe /c Google,Java /r Hardware Engineer, Automation Intern"
            + " /pay 2000-3000";
    public static final String[] OPTIONAL_PARAMETERS = {"/pay", "/ind", "/time", "/name",
                                                        "/ygoals", "/mgoals", "/c", "/r"};
    private static final Logger logger = InternSprintLogger.getLogger();

    public UserProfileCommand() {
    }

    @Override
    public String getCommandType() {
        return "user";
    }

    /**
     * Check that only predefined flags are entered by user
     *
     * @return is valid flags or not
     */
    @Override
    protected boolean isValidParameters() {
        logger.log(Level.INFO, "Entering check for parameters in user command.");
        for (String key : parameters.keySet()) {
            if (!Arrays.asList(OPTIONAL_PARAMETERS).contains(key)) {
                logger.log(Level.WARNING, "There is some invalid key found.");
                System.out.println("Invalid key found: " + key);
                return false;
            }
        }
        assert parameters.keySet().stream().allMatch(key -> Arrays.asList(OPTIONAL_PARAMETERS).contains(key))
                : "All flags should be members of set of predefined valid flags";
        return true;

    }

    /**
     * Iterates through parameters provided by user and updated according fields.
     *
     * @param internships InternshipList object
     * @param user refers to user saved in session
     * @return successful profile update or error message
     */
    @Override
    public CommandResult execute(InternshipList internships, UserProfile user) {
        logger.log(Level.INFO, "Entering execute for user command...");
        CommandResult result;
        ArrayList<String> feedback = new ArrayList<>();
        if (!isValidParameters()) {
            logger.log(Level.WARNING, "There are invalid parameters so error result is output to user.");
            feedback.add(USERPROFILE_INVALID_PARAMS);
            feedback.add(MESSAGE_USAGE);
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            return result;
        }
        if (parameters.isEmpty()){
            feedback.add(NOTE_NO_PARAMETERS);
            feedback.add(MESSAGE_USAGE);
            feedback.add(user.toString());
            result = new CommandResult(feedback);
            result.setSuccessful(true);
            return result;
        }

        boolean wrongInputByUser = setUserProfileAttributes(user,feedback);
        if (wrongInputByUser){
            feedback.add(MESSAGE_USAGE);
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            return result;
        }
        result = user.saveProfile(feedback);
        if (!result.isSuccessful()) {
            logger.log(Level.WARNING, "There was an error saving the profile.");
            return result;
        }


        feedback.add(USER_UPDATE_SUCCESS_MESSAGE);
        feedback.add(user.toString());
        result = new CommandResult(feedback);
        result.setSuccessful(true);
        return result;
    }

    private boolean setUserProfileAttributes(UserProfile user, ArrayList<String> feedback) {
        logger.log(Level.INFO, "Updating given parameters...");
        if (parameters.containsKey("/name")) {
            user.setName(parameters.get("/name"));
        }
        if (parameters.containsKey("/pay")) {
            String stipendRange = parameters.get("/pay");
            if (!isValidStipendRange(stipendRange)) {
                feedback.add(INVALID_STIPEND_RANGE);
                return true;
            } else {
                user.setTargetStipendRange(stipendRange);
            }
        }
        if (parameters.containsKey("/ind")) {
            user.setPreferredIndustries(parameters.get("/ind"));
        }
        if (parameters.containsKey("/time")) {
            user.setInternshipDateRange(parameters.get("/time"));
        }
        if (parameters.containsKey("/ygoals")) {
            user.setYearlyGoals(parameters.get("/ygoals"));
        }
        if (parameters.containsKey("/mgoals")) {
            user.setMonthlyGoals(parameters.get("/mgoals"));
        }
        if (parameters.containsKey("/c")) {
            user.setPreferredCompanies(parameters.get("/c"));
        }
        if (parameters.containsKey("/r")) {
            user.setPreferredRoles(parameters.get("/r"));
        }
        return false;
    }
    private boolean isValidStipendRange(String stipendRange) {
        try {
            String[] parts = stipendRange.trim().split("-");
            if (parts.length != 2) {
                return false;
            }

            double min = Double.parseDouble(parts[0].trim());
            double max = Double.parseDouble(parts[1].trim());
            min = Math.round(min * 100.0) / 100.0;
            max = Math.round(max * 100.0) / 100.0;
            return !(min >= max);
        } catch (NumberFormatException e) {
            return false;
        }
    }

}




