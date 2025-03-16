package seedu.internsprint.userProfile;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserProfile {
    public static String name;
    public static String yearlyGoals;
    public static String monthlyTarget;
    public static ArrayList<String> preferredIndustries;
    public static ArrayList<String> preferredCompanies;
    public static ArrayList<String> preferredRoles;
    public static String targetStipendRange;
    public static LocalDateTime[] internshipDateRange;

    public UserProfile() {
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        UserProfile.name = name;
    }

    public static String getYearlyGoals() {
        return yearlyGoals;
    }

    public static void setYearlyGoals(String yearlyGoals) {
        UserProfile.yearlyGoals = yearlyGoals;
    }

    public static String getMonthlyTarget() {
        return monthlyTarget;
    }

    public static void setMonthlyTarget(String monthlyTarget) {
        UserProfile.monthlyTarget = monthlyTarget;
    }

    public static ArrayList<String> getPreferredIndustries() {
        return preferredIndustries;
    }

    public static void setPreferredIndustries(ArrayList<String> preferredIndustries) {
        UserProfile.preferredIndustries = preferredIndustries;
    }

    public static ArrayList<String> getPreferredCompanies() {
        return preferredCompanies;
    }

    public static void setPreferredCompanies(ArrayList<String> preferredCompanies) {
        UserProfile.preferredCompanies = preferredCompanies;
    }

    public static ArrayList<String> getPreferredRoles() {
        return preferredRoles;
    }

    public static void setPreferredRoles(ArrayList<String> preferredRoles) {
        UserProfile.preferredRoles = preferredRoles;
    }

    public static String getTargetStipendRange() {
        return targetStipendRange;
    }

    public static void setTargetStipendRange(String targetStipendRange) {
        UserProfile.targetStipendRange = targetStipendRange;
    }

    public static LocalDateTime[] getInternshipDateRange() {
        return internshipDateRange;
    }

    public static void setInternshipDateRange(LocalDateTime[] internshipDateRange) {
        UserProfile.internshipDateRange = internshipDateRange;
    }
}
