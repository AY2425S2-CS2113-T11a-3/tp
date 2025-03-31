package seedu.internsprint.model.internship;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_REQUIRED_PARAMETERS;

/**
 * Represents a software internship.
 */
public class SoftwareInternship extends Internship {
    private String techStack;

    public SoftwareInternship(String companyName, String role, String techStack) {
        super(companyName, role);
        if (techStack == null || techStack.isBlank()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/tech"));
        }
        this.techStack = techStack;
    }

    public SoftwareInternship(String companyName, String role, String techStack, String eligibility,
                              String description, String status, String expectations) {
        super(companyName, role, eligibility, description, status, expectations);
        if (techStack == null || techStack.isBlank()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/tech"));
        }
        this.techStack = techStack;
    }

    /**
     * Returns a copy of the software internship.
     *
     * @return Copy of the software internship.
     */
    public SoftwareInternship copy() {
        return new SoftwareInternship(companyName, role, techStack, eligibility, description, status, expectations);
    }

    /**
     * Returns a string representation of the software internship.
     * Shows the company name, role and tech stack.
     *
     * @return String representation of the software internship.
     */
    @Override
    public String toString() {
        return "Company: " + companyName + ", Role: " + role + ", Tech: " + techStack;
    }

    /**
     * Returns a string representation of the software internship.
     * Shows all details of the software internship.
     *
     * @return String representation of the software internship.
     */
    @Override
    public ArrayList<String> toDescription() {
        ArrayList<String> internshipString = super.toDescription();
        internshipString.add("Tech Stack: " + techStack);
        return internshipString;
    }

    /**
     * Returns true if the software internship is equal to another object.
     * Two software internships are equal if they have the same company name, role and tech stack.
     * This method overrides the equals method in the Object class.
     *
     * @param obj Object to compare with.
     * @return True if the software internships are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SoftwareInternship softwareInternship = (SoftwareInternship) obj;
        return companyName.equals(softwareInternship.getCompanyName())
                && role.equals(softwareInternship.getRole())
                && techStack.equals(softwareInternship.getTechStack());
    }

    /**
     * Returns a JSON object representing the software internship.
     *
     * @return JSON object representing the software internship.
     */
    @Override
    public JSONObject toJson() {
        Map<String, Object> orderedMap = new LinkedHashMap<>();
        orderedMap.put("type", "software");
        orderedMap.put("companyName", companyName);
        orderedMap.put("role", role);
        orderedMap.put("techStack", techStack);
        orderedMap.put("eligibility", eligibility);
        orderedMap.put("description", description);
        orderedMap.put("status", status);
        orderedMap.put("expectations", expectations);
        return new JSONObject(orderedMap);
    }

    /**
     * Returns a software internship from a JSON object.
     *
     * @param json JSON object representing the software internship.
     * @return Software internship represented by the JSON object.
     */
    public static SoftwareInternship fromJson(JSONObject json) {
        return new SoftwareInternship(
                json.getString("companyName"),
                json.getString("role"),
                json.getString("techStack"),
                json.optString("eligibility", ""),
                json.optString("description", ""),
                json.optString("status", ""),
                json.optString("expectations", "")
        );
    }

    @Override
    public String getType() {
        return "software";
    }

    public String getTechStack() {
        return techStack;
    }

    public void setTechStack(String techStack) {
        this.techStack = techStack;
    }
}
