package seedu.internsprint.project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_REQUIRED_PARAMETERS;

/**
 * Represents a software project.
 */
public class SoftwareProject extends Project {
    private List<String> programmingLanguages;

    public SoftwareProject(String projectName, String role, List<String> programmingLanguages) {
        super(projectName, role);
        if (programmingLanguages == null || programmingLanguages.isEmpty()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/tech"));
        }
        for (String language : programmingLanguages) {
            if (language == null || language.isBlank()) {
                throw new IllegalArgumentException("Programming languages must not be null or blank.");
            }
        }
        this.programmingLanguages = programmingLanguages;
    }

    public SoftwareProject(String projectName, String role, List<String> programmingLanguages, String objectives,
                           String description, String duration) {
        super(projectName, role, objectives, description, duration);
        if (programmingLanguages == null || programmingLanguages.isEmpty()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/tech"));
        }
        this.programmingLanguages = programmingLanguages;
    }

    /**
     * Creates a copy of the software project.
     * @return Copy of the software project.
     */
    @Override
    public SoftwareProject copy() {
        return new SoftwareProject(projectName, role, programmingLanguages,
                objectives, description, duration);
    }

    /**
     * Returns a string representation of the software project.
     * @return String representation of the software project.
     */
    @Override
    public String toString() {
        return "Project: " + projectName + ", Role: " + role + ", Programming Languages: " + String.join(", ", programmingLanguages);
    }

    /**
     * Returns a string representation of the software project.
     * Shows all details of the software project.
     * @return String representation of the software project.
     */
    @Override
    public ArrayList<String> toDescription() {
        ArrayList<String> projectString = super.toDescription();
        projectString.add("Programming Languages: " + String.join(", ", programmingLanguages));
        return projectString;
    }

    /**
     * Returns true if the software project is equal to another object.
     * @param obj Object to compare to.
     * @return True if the software project is equal to the object, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SoftwareProject softwareProject = (SoftwareProject) obj;
        return projectName.equals(softwareProject.getProjectName())
                && role.equals(softwareProject.getRole())
                && programmingLanguages.equals(softwareProject.getProgrammingLanguages());
    }

    /**
     * Converts the software project to a JSON object.
     * @return JSON object representing the software project.
     */
    @Override
    public JSONObject toJson() {
        Map<String, Object> orderedMap = new LinkedHashMap<>();
        orderedMap.put("type", "software");
        orderedMap.put("projectName", projectName);
        orderedMap.put("role", role);
        orderedMap.put("programmingLanguages", programmingLanguages);
        orderedMap.put("objectives", objectives);
        orderedMap.put("description", description);
        orderedMap.put("duration", duration);
        return new JSONObject(orderedMap);
    }

    @Override
    public String getType() {
        return "software";
    }

    public List<String> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(List<String> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    /**
     * Returns a SoftwareProject object from a JSON object.
     * @param json JSON object representing the software project.
     * @return SoftwareProject object.
     */
    public static SoftwareProject fromJson(JSONObject json) {
        List<String> programmingLanguages = new ArrayList<>();
        json.getJSONArray("programmingLanguages").forEach(item -> {
            if (item instanceof String) {
                programmingLanguages.add((String) item);
            }
        });
        return new SoftwareProject(
                json.getString("projectName"),
                json.getString("role"),
                programmingLanguages,
                json.optString("objectives", ""),
                json.optString("description", ""),
                json.optString("duration", "")
        );
    }
}
