package seedu.internsprint.model.internship;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_REQUIRED_PARAMETERS;

/**
 * Represents a general internship.
 */
public class GeneralInternship extends Internship {
    private String department;

    public GeneralInternship(String companyName, String role, String department) {
        super(companyName, role);
        if (department == null || department.isBlank()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/dept"));
        }
        this.department = department;
    }

    public GeneralInternship(String companyName, String role, String department, String eligibility,
                             String description, String status, String expectations) {
        super(companyName, role, eligibility, description, status, expectations);
        if (department == null || department.isBlank()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/dept"));
        }
        this.department = department;
    }

    /**
     * Returns a copy of the general internship.
     *
     * @return Copy of the general internship.
     */
    public GeneralInternship copy() {
        return new GeneralInternship(companyName, role, department, eligibility, description, status, expectations);
    }

    /**
     * Returns a string representation of the general internship.
     * Shows the company name, role and department.
     *
     * @return String representation of the general internship.
     */
    @Override
    public String toString() {
        return "Company: " + companyName + ", Role: " + role + ", Dept: " + department;
    }

    /**
     * Returns a string representation of the general internship.
     * Shows all details of the general internship.
     *
     * @return String representation of the general internship.
     */
    @Override
    public ArrayList<String> toDescription() {
        ArrayList<String> internshipString = super.toDescription();
        internshipString.add("Department: " + department);
        return internshipString;
    }

    /**
     * Returns true if the general internship is equal to another object.
     * Two general internships are equal if they have the same company name, role and department.
     * This method overrides the equals method in the Object class.
     *
     * @param obj Object to compare to.
     * @return True if the general internship is equal to the other object, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        GeneralInternship generalInternship = (GeneralInternship) obj;
        return companyName.equals(generalInternship.getCompanyName())
                && role.equals(generalInternship.getRole())
                && department.equals(generalInternship.getDepartment());
    }

    /**
     * Returns a JSON object representing the general internship.
     *
     * @return JSON object representing the general internship.
     */
    @Override
    public JSONObject toJson() {
        Map<String, Object> orderedMap = new LinkedHashMap<>();
        orderedMap.put("internshipId", internshipId);
        orderedMap.put("type", "general");
        orderedMap.put("companyName", companyName);
        orderedMap.put("role", role);
        orderedMap.put("department", department);
        orderedMap.put("eligibility", eligibility);
        orderedMap.put("description", description);
        orderedMap.put("status", status);
        orderedMap.put("expectations", expectations);
        return new JSONObject(orderedMap);
    }

    /**
     * Returns a GeneralInternship object from a JSON object.
     *
     * @param json JSON object representing the general internship.
     * @return GeneralInternship object.
     */
    public static GeneralInternship fromJson(JSONObject json) {
        GeneralInternship generalInternship = new GeneralInternship(
                json.getString("companyName"),
                json.getString("role"),
                json.getString("department"),
                json.optString("eligibility", ""),
                json.optString("description", ""),
                json.optString("status", ""),
                json.optString("expectations", "")
        );
        generalInternship.setInternshipId(json.getInt("internshipId"));
        return generalInternship;
    }

    @Override
    public String getType() {
        return "general";
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
