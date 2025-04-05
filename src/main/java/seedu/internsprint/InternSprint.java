package seedu.internsprint;

import seedu.internsprint.logic.command.Command;
import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.logic.parser.CommandParser;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;
import seedu.internsprint.storage.StorageManager;
import seedu.internsprint.util.InternSprintLogger;
import seedu.internsprint.util.Ui;

import java.util.logging.Logger;
import java.util.logging.Level;


/**
 * Entry point of the InternSprint application.
 */
public class InternSprint {
    private static final Logger logger = InternSprintLogger.getLogger();
    private final StorageManager storageManager;
    private final InternshipList internships;
    private final UserProfile user;

    public InternSprint() {
        storageManager = StorageManager.getInstance();
        internships = new InternshipList();
        user = new UserProfile();
    }

    /**
     * Main entry-point for the InternSprint application.
     */
    public static void main(String[] args) {
        InternSprintLogger.getLogger();
        new InternSprint().run();
    }

    /**
     * Runs the InternSprint program until termination.
     */
    public void run() {
        logger.log(Level.INFO, "Starting InternSprint");
        Ui.showWelcomeMessage();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Loads data from storage.
     */
    private boolean loadData() {
        logger.log(Level.INFO, "Loading data from storage");
        CommandResult internshipResult = storageManager.loadInternshipData(internships);
        CommandResult interviewResult = storageManager.loadInterviewData(internships);
        CommandResult profileResult = storageManager.loadUserProfileData(user);
        CommandResult projectResult = storageManager.loadProjectData(user.projects);
        Ui.showResultToUser(internshipResult);

        return interviewResult.isSuccessful() && internshipResult.isSuccessful()
            && profileResult.isSuccessful() && projectResult.isSuccessful();
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command.
     */
    private void runCommandLoopUntilExitCommand() {
        logger.log(Level.INFO, "Loading internships from storage");
        boolean isLoadingSuccessful = loadData();
        if (!isLoadingSuccessful) {
            Ui.showError("Unable to load data from storage. Please check your file.");
            return;
        }
        logger.log(Level.INFO, "Data loaded successfully");

        CommandResult result;
        boolean isExit = false;
        while (!isExit) {
            try {
                String userCommand = Ui.getUserCommand();
                logger.log(Level.INFO, "User command: " + userCommand);
                Command command = CommandParser.parseCommand(userCommand);
                logger.log(Level.INFO, "Parsed Command: " + command);
                result = command.execute(internships, user);
                logger.log(Level.INFO, "Command executed successfully");

                Ui.showResultToUser(result);
                isExit = result.isExit();
            } catch (IllegalArgumentException e) {
                logger.log(Level.WARNING, "Invalid command entered");
                Ui.showError(e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    /**
     * Exits the program after displaying the exit message.
     */
    private void exit() {
        logger.log(Level.INFO, "Exiting InternSprint");
        System.exit(0);
    }
}
