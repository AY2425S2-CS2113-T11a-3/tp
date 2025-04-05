package seedu.internsprint.logic.command;

import seedu.internsprint.model.internship.Internship;
import seedu.internsprint.util.InternSprintLogger;

import java.util.logging.Logger;
import java.util.logging.Level;

import java.util.List;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    private static final Logger logger = InternSprintLogger.getLogger();
    private final List<String> feedbackToUser;
    private final List<Internship> relevantInternships;
    private boolean isSuccessful;
    private boolean isExit;

    /**
     * Constructs a {@code CommandResult} with multiple feedback.
     *
     * @param feedbackToUser Feedback to be shown to the user.
     */
    public CommandResult(List<String> feedbackToUser) {
        logger.log(Level.INFO, "Multiple feedback provided to user...");
        this.feedbackToUser = feedbackToUser;
        this.relevantInternships = null;
        this.isExit = false;
    }

    /**
     * Constructs a {@code CommandResult} with one feedback.
     *
     * @param feedbackToUser Feedback to be shown to the user.
     */
    public CommandResult(String feedbackToUser) {
        logger.log(Level.INFO, "One line of feedback provided to user...");
        this.feedbackToUser = List.of(feedbackToUser);
        this.relevantInternships = null;
        this.isExit = false;
    }

    /**
     * Constructs a {@code CommandResult} with multiple feedback and relevant internships.
     *
     * @param feedbackToUser Feedback to be shown to the user.
     * @param relevantInternships Internships relevant to the command.
     */
    public CommandResult(List<String> feedbackToUser, List<Internship> relevantInternships) {
        logger.log(Level.INFO, "Multiple feedback provided to user alongside some relevant internship...");
        this.feedbackToUser = feedbackToUser;
        this.relevantInternships = relevantInternships;
        this.isExit = false;
    }

    /**
     * Constructs a {@code CommandResult} with one feedback and relevant internships.
     *
     * @param feedbackToUser Feedback to be shown to the user.
     * @param relevantInternships Internships relevant to the command.
     */
    public CommandResult(String feedbackToUser, List<Internship> relevantInternships) {
        logger.log(Level.INFO, "One line of feedback provided to user alongside some relevant internship...");
        this.feedbackToUser = List.of(feedbackToUser);
        this.relevantInternships = relevantInternships;
        this.isExit = false;
    }

    public CommandResult(List<String> feedbackToUser, boolean isSuccessful) {
        logger.log(Level.INFO, "Multiple feedback provided to user and command is unsuccessful...");
        this.feedbackToUser = feedbackToUser;
        this.relevantInternships = null;
        this.isExit = false;
        this.isSuccessful = isSuccessful;
    }

    public List<String> getFeedbackToUser() {
        return feedbackToUser;
    }

    public List<Internship> getRelevantInternships() {
        return relevantInternships;
    }

    public void setSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }
}
