package seedu.internsprint.storage;

import seedu.internsprint.model.userprofile.UserProfile;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_CREATE_DIRECTORY;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_CREATE_FILE;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_LOAD_PROFILE;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_SAVE_PROFILE;
import static seedu.internsprint.util.InternSprintMessages.SAVING_PROFILE_SUCCESS;

/**
 * Handles the reading and writing of user profile data to and from the file.
 */
public class ProfileStorageHandler {
    private static final String FILE_PATH = Paths.get("data", "user.txt").toString();
    private static File userProfileFile;
    private static Logger logger = Logger.getLogger(ProfileStorageHandler.class.getName());

    public ProfileStorageHandler() {
        userProfileFile = new File(FILE_PATH);
    }

    /**
     * Saves the user profile when the application starts.
     */
    public void saveProfileOnStart(UserProfile userProfile) {
        if (userProfile != null) {
            logger.log(Level.INFO, "Saving user profile on application start...");
            saveUserProfile(userProfile);
        }
    }

    /**
     * Creates the user profile file if it does not exist.
     */
    public static void createUserProfileFile() {
        try {
            if (userProfileFile.getParentFile() != null && !userProfileFile.getParentFile().exists()) {
                if (!userProfileFile.getParentFile().mkdirs()) {
                    throw new RuntimeException(UNABLE_TO_CREATE_DIRECTORY);
                }
                if (!userProfileFile.createNewFile()) {
                    throw new RuntimeException(UNABLE_TO_CREATE_FILE);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, UNABLE_TO_CREATE_FILE, e);
            throw new RuntimeException(UNABLE_TO_CREATE_FILE);
        }
    }

    /**
     * Saves the user profile to the file in tabular format.
     * @param userProfile the user profile to save.
     */
    public void saveUserProfile(UserProfile userProfile) {
        logger.log(Level.INFO, "Saving user profile to file ...");
        String userProfileData = userProfile.toFormattedString();
        if (!userProfileFile.exists()) {
            createUserProfileFile();
        }
        try (FileWriter fileWriter = new FileWriter(userProfileFile)) {
            fileWriter.write(userProfileData);
            logger.log(Level.INFO, String.format(SAVING_PROFILE_SUCCESS, userProfileFile.getAbsolutePath()));
        } catch (IOException e) {
            logger.log(Level.SEVERE, String.format(UNABLE_TO_SAVE_PROFILE, userProfileFile.getAbsolutePath()), e);
            throw new RuntimeException(String.format(UNABLE_TO_SAVE_PROFILE, userProfileFile.getAbsolutePath()));
        }
    }

    /**
     * Loads the user profile from the file.
     * @return the loaded user profile.
     */
    public UserProfile loadUserProfile() {
        logger.log(Level.INFO, "Loading user profile from file ...");
        if (!userProfileFile.exists() || userProfileFile.length() == 0) {
            return null;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(userProfileFile))) {
            StringBuilder profileData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                profileData.append(line).append("\n");
            }

            UserProfile userProfile = new UserProfile();

            String[] lines = profileData.toString().split("\n");
            for (String profileLine : lines) {
                if (profileLine.startsWith("Name: ")) {
                    userProfile.setName(profileLine.substring(6));
                } else if (profileLine.startsWith("Yearly Goals: ")) {
                    userProfile.setYearlyGoals(profileLine.substring(14));
                } else if (profileLine.startsWith("Monthly Goals: ")) {
                    userProfile.setMonthlyGoals(profileLine.substring(15));
                } else if (profileLine.startsWith("Preferred Industries: ")) {
                    userProfile.setPreferredIndustries(profileLine.substring(22));
                } else if (profileLine.startsWith("Preferred Companies: ")) {
                    userProfile.setPreferredCompanies(profileLine.substring(21));
                } else if (profileLine.startsWith("Preferred Roles: ")) {
                    userProfile.setPreferredRoles(profileLine.substring(18));
                } else if (profileLine.startsWith("Target Stipend Range: ")) {
                    userProfile.setTargetStipendRange(profileLine.substring(22));
                } else if (profileLine.startsWith("Internship Date Range: ")) {
                    userProfile.setInternshipDateRange(profileLine.substring(23));
                }
            }
            return userProfile;
        } catch (IOException e) {
            logger.log(Level.SEVERE, UNABLE_TO_LOAD_PROFILE, e);
            throw new RuntimeException(UNABLE_TO_LOAD_PROFILE);
        }
    }

}
