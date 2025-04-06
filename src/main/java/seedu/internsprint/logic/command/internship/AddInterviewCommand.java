package seedu.internsprint.logic.command.internship;

import seedu.internsprint.exceptions.DuplicateEntryException;
import seedu.internsprint.logic.command.Command;
import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.logic.parser.CommandParser;
import seedu.internsprint.model.internship.Internship;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.internship.interview.Interview;
import seedu.internsprint.model.userprofile.UserProfile;
import seedu.internsprint.util.InternSprintLogger;
import seedu.internsprint.util.InternSprintMessages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.internsprint.util.InternSprintExceptionMessages.ADD_INTERVIEW_INVALID_PARAMS;
import static seedu.internsprint.util.InternSprintExceptionMessages.DESC_UNABLE_TO_FIND_INTERNSHIP;
import static seedu.internsprint.util.InternSprintMessages.ADD_INTERVIEW_MESSAGE_SUCCESS;

/*
 * Represents a command to add an interview to an internship.
 */
public class AddInterviewCommand extends Command {
    public static final String COMMAND_WORD = "interview for";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an interview to the internship list.\n"
        + "    Parameters: " + "/index INDEX_OF_INTERNSHIP /date DATE /start START_TIME /end END_TIME /type TYPE\n"
        + "    [/email INTERVIEWER_EMAIL] [/notes NOTES]\n"
        + "    Example: " + COMMAND_WORD + " /index 1 /date 2021-10-10 /start 10:00 /end 11:00 /type Coding";
    public static final String[] REQUIRED_PARAMETERS = {"/index", "/date", "/start", "/end", "/type"};
    public static final String[] OPTIONAL_PARAMETERS = {"/email", "/notes"};

    private static final Logger logger = InternSprintLogger.getLogger();

    /**
     * Checks if the parameters entered by the user are valid.
     *
     * @return True if the parameters are valid, false otherwise.
     */
    @Override
    protected boolean isValidParameters() {
        logger.info("Entering check for parameters in add interview command.");
        if (!parameters.containsKey(REQUIRED_PARAMETERS[0])) {
            logger.severe("There is no specified index.");
            return false;
        }
        assert parameters.containsKey(REQUIRED_PARAMETERS[0]) : "/index flag should be present in the command";
        for (String requiredParameter : REQUIRED_PARAMETERS) {
            if (!parameters.containsKey(requiredParameter)) {
                logger.severe("There is a missing required parameter.");
                return false;
            }
        }
        for (String parameter : parameters.keySet()) {
            if (!Arrays.asList(REQUIRED_PARAMETERS).contains(parameter)
                && !Arrays.asList(OPTIONAL_PARAMETERS).contains(parameter)) {
                logger.severe("There is a flag that is out of specified optional parameters.");
                return false;
            }
        }
        return true;
    }

    @Override
    public String getCommandType() {
        return "internship";
    }

    /**
     * Executes the command to add an interview to an internship.
     *
     * @param internships InternshipList or UserProfile user.
     * @param user UserProfile object.
     * @return CommandResult object.
     */
    @Override
    public CommandResult execute(InternshipList internships, UserProfile user) {
        logger.info("Executing add interview command...");
        CommandResult result;
        List<String> feedback = new ArrayList<>();

        if (!isValidParameters()) {
            logger.warning("Invalid parameters in add interview command.");
            feedback.add(ADD_INTERVIEW_INVALID_PARAMS);
            feedback.add(MESSAGE_USAGE);
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            return result;
        }

        String[] validIndex;
        try {
            validIndex = CommandParser.validateIndex(parameters.get("/index"), internships);
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Index for edit command out of range...");
            feedback.add(e.getMessage());
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            return result;
        }

        int index = Integer.parseInt(validIndex[1]);
        String type = validIndex[0];
        assert (index >= 0 && index < internships.getInternshipCount()) : "index value is within appropriate range";

        Internship internship = getInternship(feedback, internships, type, index);
        if (internship == null) {
            return new CommandResult(feedback, false);
        }

        return createAndAddInterview(feedback, internship, internships);
    }

    /**
     * Gets the internship object from the internship list.
     *
     * @param feedback Feedback to be shown to the user.
     * @param internships InternshipList object.
     * @param type Type of internship.
     * @param index Index of internship.
     * @return Internship object.
     */
    private Internship getInternship(List<String> feedback, InternshipList internships, String type, int index) {
        assert (index >= 0 && index < internships.getInternshipCount()) : "index value is within appropriate range";

        HashMap<String, ArrayList<Internship>> internshipMap = internships.getInternshipMap();
        Internship internship = internshipMap.get(type).get(index);

        if (internship == null) {
            logger.warning("Internship with specified details not found in add interview command.");
            feedback.add(DESC_UNABLE_TO_FIND_INTERNSHIP);
        }
        return internship;
    }

    /**
     * Creates an interview object and adds it to the internship.
     *
     * @param feedback Feedback to be shown to the user.
     * @param internship Internship object.
     * @param internships InternshipList object.
     * @return CommandResult object.
     */
    protected CommandResult createAndAddInterview(List<String> feedback, Internship internship,
                                                InternshipList internships) {
        Interview interview = null;
        try {
            interview = new Interview(
                parameters.get("/date"),
                parameters.get("/start"),
                parameters.get("/end"),
                parameters.get("/type"),
                parameters.getOrDefault("/email", ""),
                parameters.getOrDefault("/notes", "")
            );
        } catch (IllegalArgumentException e) {
            logger.severe("Date or time format is invalid while adding an interview.");
            feedback.add(e.getMessage());
            return new CommandResult(feedback, false);
        }
        interview.setInternshipId(internship.getInternshipId());

        try {
            internship.addInterview(interview);
            internships.saveInternships();
            feedback.add(InternSprintMessages.SAVE_SUCCESS_MESSAGE);
        } catch (IOException e) {
            logger.severe("Error saving internships to file after adding an interview.");
            feedback.add(e.getMessage());
            return new CommandResult(feedback, false);
        } catch (DuplicateEntryException e) {
            logger.warning("Interview already exists in the internship.");
            feedback.add(e.getMessage());
            return new CommandResult(feedback, false);
        }

        feedback.add(String.format(ADD_INTERVIEW_MESSAGE_SUCCESS, interview));
        return new CommandResult(feedback, true);
    }
}
