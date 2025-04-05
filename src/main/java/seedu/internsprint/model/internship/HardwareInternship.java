package seedu.internsprint.model.internship;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_REQUIRED_PARAMETERS;

/**
 * Represents a hardware internship.
 */
public class HardwareInternship extends Internship {
    private String embeddedSystems;

    public HardwareInternship(String companyName, String role, String embeddedSystems) {
        super(companyName, role);
        if (embeddedSystems == null || embeddedSystems.isBlank()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/tech"));
        }
        this.embeddedSystems = embeddedSystems;
    }

    public HardwareInternship(String companyName, String role, String embeddedSystems, String eligibility,
                              String description, String status, String expectations) {
        super(companyName, role, eligibility, description, status, expectations);
        if (embeddedSystems == null || embeddedSystems.isBlank()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/tech"));
        }
        this.embeddedSystems = embeddedSystems;
    }

    /**
     * Returns a copy of the hardware internship.
     *
     * @return Copy of the hardware internship.
     */
    public HardwareInternship copy() {
        return new HardwareInternship(companyName, role, embeddedSystems, eligibility,
            description, status, expectations);
    }

    /**
     * Returns a string representation of the hardware internship.
     * Shows the company name, role and embedded systems.
     *
     * @return String representation of the hardware internship.
     */
    @Override
    public String toString() {
        return "Company: " + companyName + ", Role: " + role + ", Tech: " + embeddedSystems;
    }

    /**
     * Returns a string representation of the hardware internship.
     * Shows all details of the hardware internship.
     *
     * @return String representation of the hardware internship.
     */
    @Override
    public ArrayList<String> toDescription() {
        ArrayList<String> internshipString = super.toDescription();
        internshipString.add("Tech: " + embeddedSystems);
        return internshipString;
    }

    /**
     * Returns true if the hardware internship is equal to another object.
     * Two hardware internships are equal if they have the same company name, role and embedded systems.
     * This method overrides the equals method in the Object class.
     *
     * @param obj Object to compare to.
     * @return True if the hardware internship is equal to the object, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        HardwareInternship hardwareInternship = (HardwareInternship) obj;
        return companyName.equals(hardwareInternship.getCompanyName())
                && role.equals(hardwareInternship.getRole())
                && embeddedSystems.equals(hardwareInternship.getEmbeddedSystems());
    }

    /**
     * Converts the hardware internship to a JSON object.
     *
     * @return JSON object representing the hardware internship.
     */
    @Override
    public JSONObject toJson() {
        Map<String, Object> orderedMap = new LinkedHashMap<>();
        orderedMap.put("internshipId", internshipId);
        orderedMap.put("type", "hardware");
        orderedMap.put("companyName", companyName);
        orderedMap.put("role", role);
        orderedMap.put("embeddedSystems", embeddedSystems);
        orderedMap.put("eligibility", eligibility);
        orderedMap.put("description", description);
        orderedMap.put("status", status);
        orderedMap.put("expectations", expectations);
        return new JSONObject(orderedMap);
    }

    /**
     * Converts a JSON object to a hardware internship.
     *
     * @param json JSON object representing the hardware internship.
     * @return Hardware internship represented by the JSON object.
     */
    public static HardwareInternship fromJson(JSONObject json) {
        HardwareInternship hardwareInternship = new HardwareInternship(
                json.getString("companyName"),
                json.getString("role"),
                json.getString("embeddedSystems"),
                json.optString("eligibility", ""),
                json.optString("description", ""),
                json.optString("status", ""),
                json.optString("expectations", "")
        );
        hardwareInternship.setInternshipId(json.getInt("internshipId"));
        return hardwareInternship;
    }

    @Override
    public String getType() {
        return "hardware";
    }

    public String getEmbeddedSystems() {
        return embeddedSystems;
    }

    public void setEmbeddedSystems(String embeddedSystems) {
        this.embeddedSystems = embeddedSystems;
    }
}
