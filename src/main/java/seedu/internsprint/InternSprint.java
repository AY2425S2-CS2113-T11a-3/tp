package seedu.internsprint;

import seedu.internsprint.command.Command;
import seedu.internsprint.command.CommandResult;
import seedu.internsprint.handler.CommandParser;
import seedu.internsprint.handler.StorageHandler;
import seedu.internsprint.internship.InternshipList;
import seedu.internsprint.util.Ui;

import java.util.logging.Logger;
import java.util.logging.Level;


/**
 * Entry point of the InternSprint application.
 */
public class InternSprint {
    private static final Logger logger = Logger.getLogger(InternSprint.class.getName());
    private final InternshipList internships;

    public InternSprint() {
        internships = new InternshipList();
    }

    /**
     * Main entry-point for the InternSprint application.
     */
    public static void main(String[] args) {
        Logger.getLogger("").setLevel(Level.OFF);
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

                result = command.execute(internships);
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
