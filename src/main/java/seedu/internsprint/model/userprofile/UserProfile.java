package seedu.internsprint.model.userprofile;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import de.vandermeer.asciitable.AsciiTable;

import seedu.internsprint.logic.parser.CommandParser;
import seedu.internsprint.model.userprofile.project.ProjectList;
import seedu.internsprint.util.InternSprintLogger;

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
    private static final Logger logger = InternSprintLogger.getLogger();
    public final ProjectList projects;

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

}
