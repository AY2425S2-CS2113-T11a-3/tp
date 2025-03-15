package seedu.internsprint.internship;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_REQUIRED_PARAMETERS;

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
        super(companyName, role, eligibility,description,status,expectations);
        if (embeddedSystems == null || embeddedSystems.isBlank()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/tech"));
        }
        this.embeddedSystems = embeddedSystems;
    }

    public void setEmbeddedSystems(String embeddedSystems) {
        this.embeddedSystems = embeddedSystems;
    }

    @Override
    public String toString() {
        String internshipString =   "Company: " + companyName + ", Role: " + role + ", Tech: " + embeddedSystems;
        if (eligibility != null && !eligibility.isBlank()) {
            internshipString += ", Eligibility: "+eligibility;
        }
        if (description != null && !description.isBlank()) {
            internshipString += ", Description: "+description;
        }
        if (status != null && !status.isBlank()) {
            internshipString += ", Status: "+status;
        }
        if (expectations != null && !expectations.isBlank()) {
            internshipString += ", Expectations: "+expectations;
        }
        return internshipString;
    }

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

    @Override
    public JSONObject toJson() {
        Map<String, Object> orderedMap = new LinkedHashMap<>();
        orderedMap.put("type", "hardware");
        orderedMap.put("companyName", companyName);
        orderedMap.put("role", role);
        orderedMap.put("embeddedSystems", embeddedSystems);
        return new JSONObject(orderedMap);
    }

    public static HardwareInternship fromJson(JSONObject json) {
        return new HardwareInternship(
                json.getString("companyName"),
                json.getString("role"),
                json.getString("embeddedSystems")
        );
    }

    @Override
    public String getType() {
        return "hardware";
    }

    public String getEmbeddedSystems() {
        return embeddedSystems;
    }
}
