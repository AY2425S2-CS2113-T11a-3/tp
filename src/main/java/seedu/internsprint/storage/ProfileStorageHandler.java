package seedu.internsprint.storage;

import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.userprofile.UserProfile;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_CREATE_DIRECTORY;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_CREATE_FILE;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_SAVE_PROFILE;
import static seedu.internsprint.util.InternSprintExceptionMessages.CORRUPTED_PROFILE_FILE;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_LOAD_PROFILE;
import static seedu.internsprint.util.InternSprintMessages.SAVING_PROFILE_SUCCESS;

/**
 * Handles the storage of user profile data.
 */
public class ProfileStorageHandler implements Storage<UserProfile> {
    public static final String FILE_PATH = Paths.get("data", "user.txt").toString();
    private static final File userProfileFile = new File(FILE_PATH);
    private static final Logger logger = Logger.getLogger(ProfileStorageHandler.class.getName());

    public ProfileStorageHandler() {
        createFile();
    }

    /**
     * Creates the file if it does not exist.
     */
    @Override
    public void createFile() {
        try {
            if (!userProfileFile.getParentFile().exists() && !userProfileFile.getParentFile().mkdirs()) {
                throw new RuntimeException(UNABLE_TO_CREATE_DIRECTORY);
            }
            if (!userProfileFile.exists() && !userProfileFile.createNewFile()) {
                throw new RuntimeException(UNABLE_TO_CREATE_FILE);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, UNABLE_TO_CREATE_FILE, e);
            throw new RuntimeException(UNABLE_TO_CREATE_FILE);
        }
    }

    /**
     * Saves the user profile to the file.
     *
     * @param userProfile The user profile to be saved.
     */
    @Override
    public void save(UserProfile userProfile) throws IOException {
        logger.log(Level.INFO, "Saving user profile to file...");
        if (userProfile == null) {
            return;
        }

        try (FileWriter fileWriter = new FileWriter(userProfileFile)) {
            fileWriter.write(userProfile.toFormattedString());
            logger.log(Level.INFO, String.format(SAVING_PROFILE_SUCCESS, userProfileFile.getAbsolutePath()));
        } catch (IOException e) {
            logger.log(Level.SEVERE, String.format(UNABLE_TO_SAVE_PROFILE, userProfileFile.getAbsolutePath()), e);
            throw new IOException(String.format(UNABLE_TO_SAVE_PROFILE, userProfileFile.getAbsolutePath()), e);
        }
    }

    /**
     * Loads the user profile from the file.
     *
     * @param userProfile The user profile object to store the loaded data.
     * @return CommandResult object indicating the success of the operation.
     */
    @Override
    public CommandResult load(UserProfile userProfile) {
        logger.log(Level.INFO, "Loading user profile from file...");
        if (!userProfileFile.exists() || userProfileFile.length() == 0) {
            return new CommandResult(Collections.singletonList("User profile file is empty."), true);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(userProfileFile))) {
            StringBuilder profileData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                profileData.append(line).append("\n");
            }
            try {
                parseUserProfile(userProfile, profileData.toString());
            } catch (RuntimeException e) {
                logger.log(Level.WARNING, "Corrupted user profile entry: " + e.getMessage());
                List<String> feedback = new ArrayList<>();
                feedback.add(CORRUPTED_PROFILE_FILE);
                String[] lines = profileData.toString().split("\n");
                List<String> corruptedLines = new ArrayList<>();

                for (int i = 0; i < lines.length; i++) {
                    try {
                        parseUserProfile(userProfile, lines[i]);
                    } catch (RuntimeException lineException) {
                        corruptedLines.add("Corruption at line " + (i + 1) + " -> " + lines[i]);
                    }
                }

                if (!corruptedLines.isEmpty()) {
                    feedback.addAll(corruptedLines);
                } else {
                    feedback.add("Unexpected corruption occurred.");
                }

                feedback.add("Please fix or delete the file at: " + userProfileFile.getAbsolutePath());
                CommandResult result = new CommandResult(feedback);
                result.setSuccessful(false);
                return result;
            }
            return new CommandResult(Collections.singletonList("User profile loaded successfully."), true);
        } catch (IOException e) {
            logger.log(Level.SEVERE, UNABLE_TO_LOAD_PROFILE, e);
            throw new RuntimeException(UNABLE_TO_LOAD_PROFILE);
        }
    }

    /**
     * Parses the user profile data.
     *
     * @param userProfile The user profile object to store the parsed data.
     * @param profileData The string containing user profile data to be parsed.
     */
    private void parseUserProfile(UserProfile userProfile, String profileData) {
        String[] lines = profileData.split("\n");

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            try {
                if (line.startsWith("Name: ")) {
                    userProfile.setName(line.substring(6));
                } else if (line.startsWith("Yearly Goals: ")) {
                    userProfile.setYearlyGoals(line.substring(14));
                } else if (line.startsWith("Monthly Goals: ")) {
                    userProfile.setMonthlyGoals(line.substring(15));
                } else if (line.startsWith("Preferred Industries: ")) {
                    userProfile.setPreferredIndustries(line.substring(22));
                } else if (line.startsWith("Preferred Companies: ")) {
                    userProfile.setPreferredCompanies(line.substring(21));
                } else if (line.startsWith("Preferred Roles: ")) {
                    userProfile.setPreferredRoles(line.substring(17));
                } else if (line.startsWith("Target Stipend Range: ")) {
                    userProfile.setTargetStipendRange(line.substring(22));
                } else if (line.startsWith("Internship Date Range: ")) {
                    userProfile.setInternshipDateRange(line.substring(23));
                } else if (!line.trim().isEmpty()) {
                    throw new RuntimeException("Unexpected line in user profile at line "
                            + (i + 1) + ": '" + line + "'");
                }
            } catch (RuntimeException e) {
                throw new RuntimeException("Error processing line " + (i + 1) + ": '" + line + "' - " + e.getMessage());
            }
        }
    }

}
