package seedu.internsprint.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class InternSprintLogger {
    private static Logger rootLogger;
    private static final String FILE_PATH = "./log/InternSprint.log";
    private static boolean isConfigured = false;

    private static void setUpLogger() {
        if (isConfigured) {
            return;
        }
        rootLogger = Logger.getLogger("");

        // Remove any default handlers (to avoid duplicate logs)
        for (var handler : rootLogger.getHandlers()) {
            rootLogger.removeHandler(handler);
        }

        // Create a ConsoleHandler for output to the console
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.OFF);
        consoleHandler.setFormatter(new SimpleFormatter());
        rootLogger.addHandler(consoleHandler);

        // Create a FileHandler to log messages to a file
        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                boolean dirCreated = file.getParentFile().mkdirs();
                boolean fileCreated = file.createNewFile();

                if (!fileCreated || !dirCreated) {
                    throw new FileNotFoundException();
                }
            }

            FileHandler fileHandler = new FileHandler(FILE_PATH, true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            rootLogger.addHandler(fileHandler);
        } catch (IOException e) {
            rootLogger.log(Level.SEVERE, "Unable to configure logging", e);
        }
        isConfigured = true;
    }

    public static Logger getLogger() {
        if (rootLogger == null) {
            setUpLogger();
        }
        return rootLogger;
    }
}
