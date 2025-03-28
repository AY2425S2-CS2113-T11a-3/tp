package seedu.internsprint;

import seedu.internsprint.logic.command.Command;
import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.logic.parser.CommandParser;
import seedu.internsprint.storage.StorageHandler;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;
import seedu.internsprint.util.InternSprintLogger;
import seedu.internsprint.util.Ui;

import java.util.logging.Logger;
import java.util.logging.Level;


/**
 * Entry point of the InternSprint application.
 */
public class InternSprint {
    private static final Logger logger = Logger.getLogger(InternSprint.class.getName());
    private final InternshipList internships;
    private final UserProfile user;

    public InternSprint() {
        internships = new InternshipList();
        user = new UserProfile();
    }

    /**
     * Main entry-point for the InternSprint application.
     */
    public static void main(String[] args) {
        //Logger.getLogger("").setLevel(Level.OFF);
        // Set up centralized logger configuration at startup.
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
     * Reads the user command and executes it, until the user issues the exit command.
     */
    private void runCommandLoopUntilExitCommand() {
        logger.log(Level.INFO, "Loading internships from storage");
        CommandResult result = StorageHandler.loadInternships(internships);
        Ui.showResultToUser(result);
        logger.log(Level.INFO, "Internships loaded successfully");

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
        Ui.showExitMessage();
        System.exit(0);
    }
}
