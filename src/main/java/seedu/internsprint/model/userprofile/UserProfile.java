package seedu.internsprint.model.userprofile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import de.vandermeer.asciitable.AsciiTable;

import seedu.internsprint.logic.command.Command;
import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.logic.parser.CommandParser;
import seedu.internsprint.model.userprofile.project.ProjectList;
import seedu.internsprint.storage.InternshipStorageHandler;
import seedu.internsprint.storage.ProfileStorageHandler;
import seedu.internsprint.storage.StorageManager;
import seedu.internsprint.util.InternSprintLogger;

import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_WRITE_FILE;

/**
 * Stores basic user profile to customize application for user.
 */
public class UserProfile {
    public static String name;
    public static String yearlyGoals;
    public static String monthlyGoals;
    public static ArrayList<String> preferredIndustries;
    public static ArrayList<String> preferredCompanies;
    public static ArrayList<String> preferredRoles;
    public static String targetStipendRange;
    public static String internshipDateRange;
    public final ProjectList projects;

    private static final Logger logger = InternSprintLogger.getLogger();
    private final StorageManager storageManager = StorageManager.getInstance();

    public UserProfile() {
        projects = new ProjectList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        UserProfile.name = name;
    }

    public String getYearlyGoals() {
        return yearlyGoals;
    }

    public void setYearlyGoals(String yearlyGoals) {
        UserProfile.yearlyGoals = yearlyGoals;
    }

    public String getMonthlyGoals() {
        return monthlyGoals;
    }

    public void setMonthlyGoals(String monthlyGoals) {
        UserProfile.monthlyGoals = monthlyGoals;
    }

    public ArrayList<String> getPreferredIndustries() {
        return preferredIndustries;
    }

    public void setPreferredIndustries(String preferredIndustriesString) {
        UserProfile.preferredIndustries = CommandParser.splitToWords(preferredIndustriesString);
    }

    public ArrayList<String> getPreferredCompanies() {
        return preferredCompanies;
    }

    public void setPreferredCompanies(String preferredCompaniesString) {
        UserProfile.preferredCompanies = CommandParser.splitToWords(preferredCompaniesString);
    }

    public ArrayList<String> getPreferredRoles() {
        return preferredRoles;
    }

    public void setPreferredRoles(String preferredRolesString) {
        UserProfile.preferredRoles = CommandParser.splitToWords(preferredRolesString);
    }

    public String getTargetStipendRange() {
        return targetStipendRange;
    }

    public void setTargetStipendRange(String targetStipendRange) {
        UserProfile.targetStipendRange = targetStipendRange;
    }

    public String getInternshipDateRange() {
        return internshipDateRange;
    }

    public void setInternshipDateRange(String internshipDateRange) {
        UserProfile.internshipDateRange = internshipDateRange;
    }

    /**
     * Return basic formatted string of User Profile to user for updates
     *
     * @return String of user profile
     */
    @Override
    public String toString() {
        logger.log(Level.INFO, "Returning basic string for user profile");

        StringBuilder sb = new StringBuilder();
        if (name != null) {
            sb.append("Name: ").append(name);
        }
        if (preferredIndustries != null) {
            sb.append(", Preferred Industries: ").append(preferredIndustries);
        }
        if (preferredCompanies != null) {
            sb.append(", Preferred Companies: ").append(preferredCompanies);
        }
        if (preferredRoles != null) {
            sb.append(", Preferred Roles: ").append(preferredRoles);
        }
        if (targetStipendRange != null) {
            sb.append(", Target Stipend Range: ").append(targetStipendRange);
        }
        if (internshipDateRange != null) {
            sb.append(", Internship Date Range: ").append(internshipDateRange);
        }
        if (monthlyGoals != null) {
            sb.append(", Monthly Goals: ").append(monthlyGoals);
        }
        if (yearlyGoals != null) {
            sb.append(", Yearly Goals: ").append(yearlyGoals);
        }
        return sb.toString().replaceFirst("^,\\s*", "");
    }

    /**
     * Returns more CV-style formatted string to user in terminal
     *
     * @return CV-formatted string to user
     */
    public String toExtendedString() {
        AsciiTable at = new AsciiTable();
        at.addRule();

        String name = getName() != null ? getName() : "N/A";
        at.addRow("Name", name);
        at.addRule();

        at.addRow("Preferred Industries",
                getPreferredIndustries() != null && !getPreferredIndustries().isEmpty() ?
                        String.join(", ", getPreferredIndustries()) : "N/A");
        at.addRule();

        at.addRow("Preferred Companies",
                getPreferredCompanies() != null && !getPreferredCompanies().isEmpty() ?
                        String.join(", ", getPreferredCompanies()) : "N/A");
        at.addRule();

        at.addRow("Preferred Roles",
                getPreferredRoles() != null && !getPreferredRoles().isEmpty() ?
                        String.join(", ", getPreferredRoles()) : "N/A");
        at.addRule();

        at.addRow("Target Stipend Range",
                getTargetStipendRange() != null ? getTargetStipendRange() : "N/A");
        at.addRule();

        at.addRow("Internship Date Range", getInternshipDateRange() != null ?
                getInternshipDateRange() : "N/A");
        at.addRule();

        at.addRow("Monthly Goals", getMonthlyGoals() != null && !getMonthlyGoals().isEmpty() ?
                getMonthlyGoals().trim() : "N/A");
        at.addRule();

        at.addRow("Yearly Goals", getYearlyGoals() != null && !getYearlyGoals().isEmpty() ?
                getYearlyGoals().trim() : "N/A");
        at.addRule();

        return "\n" + at.render();

    }

    /**
     * Returns a formatted string representation of the user profile.
     * This method presents the user's details in a structured, readable format,
     * with each attribute displayed on a new line.
     *
     * @return A formatted string containing user profile details.
     */
    public String toFormattedString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Name: ").append(name != null ? name : "N/A").append("\n");
        sb.append("Yearly Goals: ").append(yearlyGoals != null ? yearlyGoals : "N/A").append("\n");
        sb.append("Monthly Goals: ").append(monthlyGoals != null ? monthlyGoals : "N/A").append("\n");
        sb.append("Preferred Industries: ").append(preferredIndustries != null ?
                String.join(", ", preferredIndustries) : "N/A").append("\n");
        sb.append("Preferred Companies: ").append(preferredCompanies != null ?
                String.join(", ", preferredCompanies) : "N/A").append("\n");
        sb.append("Preferred Roles: ").append(preferredRoles != null ?
                String.join(", ", preferredRoles) : "N/A").append("\n");
        sb.append("Target Stipend Range: ").append(targetStipendRange != null ?
                targetStipendRange : "N/A").append("\n");
        sb.append("Internship Date Range: ").append(internshipDateRange != null ?
                internshipDateRange : "N/A").append("\n");

        return sb.toString();
    }

    /**
     * Returns a JSON representation of the user profile.
     *
     * @return A JSONObject containing user profile details.
     */
    public JSONObject toJson() {
        Map<String, Object> orderedMap = new LinkedHashMap<>();
        orderedMap.put("type", "user");
        orderedMap.put("name", name);
        orderedMap.put("role", preferredRoles);
        orderedMap.put("companies", preferredCompanies);
        orderedMap.put("industries", preferredIndustries);
        orderedMap.put("monthly goals", monthlyGoals);
        orderedMap.put("yearly goals", yearlyGoals);
        orderedMap.put("pay", targetStipendRange);
        orderedMap.put("date", internshipDateRange);
        return new JSONObject(orderedMap);
    }

    /**
     * Saves the user profile to a file.
     */
    public CommandResult saveProfile(ArrayList<String> feedback) {
        logger.log(Level.INFO, "Saving user profile to file...");
        CommandResult result;
        try {
            storageManager.saveUserProfileData(this);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving user profile", e);
            feedback.add(String.format(UNABLE_TO_WRITE_FILE, ProfileStorageHandler.FILE_PATH));
            result = new CommandResult(feedback, false);
            return result;
        }
        return new CommandResult(feedback, true);
    }

}
