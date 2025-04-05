package seedu.internsprint.logic.command.internship;

import seedu.internsprint.exceptions.DuplicateEntryException;
import seedu.internsprint.logic.command.Command;
import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.internship.Internship;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;
import seedu.internsprint.util.InternSprintLogger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.internsprint.util.InternSprintMessages.ADD_MESSAGE_SUCCESS;
import static seedu.internsprint.util.InternSprintMessages.LIST_COUNT_MESSAGE;

/**
 * Represents a command to add an internship.
 */
public abstract class AddInternshipCommand extends Command {
    protected static Logger logger = InternSprintLogger.getLogger();
    protected final Set<String> requiredParameters;
    protected final Set<String> optionalParameters;

    public AddInternshipCommand(Set<String> requiredParameters, Set<String> optionalParameters) {
        this.requiredParameters = requiredParameters;
        this.optionalParameters = optionalParameters;
    }

    @Override
    public String getCommandType() {
        return "internship";
    }

    /**
     * Checks if the parameters entered by the user are valid.
     *
     * @return True if the parameters are valid, false otherwise.
     */
    @Override
    protected boolean isValidParameters() {
        return parameters.keySet().containsAll(requiredParameters);
    }

    /**
     * Gets the usage message for the command.
     *
     * @return Usage message.
     */
    protected abstract String getUsageMessage();

    /**
     * Creates an internship object.
     *
     * @return Internship object.
     */
    protected abstract Internship createInternship();


    /**
     * Executes the command to add an internship.
     *
     * @param internships InternshipList object.
     * @param user Userprofile object.
     * @return CommandResult object.
     */
    @Override
    public CommandResult execute(InternshipList internships, UserProfile user) {
        logger.log(Level.INFO, "Executing add command");
        CommandResult result;
        if (!isValidParameters()) {
            logger.log(Level.SEVERE, "Invalid parameters entered");
            result = new CommandResult(getUsageMessage());
            result.setSuccessful(false);
            return result;
        }

        Internship toAdd;
        toAdd = createInternship();

        List<String> feedback = new ArrayList<>();

        try {
            internships.addInternship(toAdd);
            internships.saveInternships();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving internships to file after adding an internship");
            feedback.add(e.getMessage());
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            return result;
        } catch (DuplicateEntryException e) {
            logger.log(Level.WARNING, "New internship is already present in the list");
            feedback.add(e.getMessage());
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            return result;
        }

        logger.log(Level.INFO, "Internship added successfully and saved to file");
        feedback.add(ADD_MESSAGE_SUCCESS);
        feedback.add(toAdd.toString());
        feedback.add(String.format(LIST_COUNT_MESSAGE, internships.getInternshipCount()));
        result = new CommandResult(feedback);
        result.setSuccessful(true);
        return result;
    }
}
