package seedu.internsprint.model.userprofile.project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import de.vandermeer.asciitable.AsciiTable;
import org.json.JSONObject;

import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_REQUIRED_PARAMETERS;

/**
 * Represents a software project.
 */
public class SoftwareProject extends Project {
    private List<String> programmingLanguages;


    public SoftwareProject(String projectName, String role, List<String> programmingLanguages, String objectives,
                           String description, String duration) {
        super(projectName, role, objectives, description, duration);
        if (programmingLanguages == null || programmingLanguages.isEmpty()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/pro"));
        }
        this.programmingLanguages = programmingLanguages;
    }

    /**
     * Creates a copy of the software project.
     *
     * @return Copy of the software project.
     */
    @Override
    public SoftwareProject copy() {
        return new SoftwareProject(projectName, role, programmingLanguages,
                objectives, description, duration);
    }

    /**
     * Returns a string representation of the software project.
     *
     * @return String representation of the software project.
     */
    @Override
    public String toString() {
        return "Project: " + projectName + ", Role: " + role +
                ", Programming Languages: " + String.join(", ", programmingLanguages)
                + ", Objectives: " + objectives + ", Duration: " + duration + ", Description: " + description;
    }

    /**
     * Returns a string representation of the software project.
     * Shows all details of the software project.
     *
     * @return String representation of the software project.
     */
    @Override
    public String toDescription() {
        AsciiTable at = new AsciiTable();
        at.addRule();

        String name = getProjectName() != null ? getProjectName() : "N/A";
        at.addRow("Project: ", name);
        at.addRule();

        at.addRow("Role: ", getRole() != null ? getRole() : "N/A");
        at.addRule();

        at.addRow("Objectives:", getObjectives() != null ? getObjectives() : "N/A");
        at.addRule();

        at.addRow("Description:", getDescription() != null ? getDescription() : "N/A");
        at.addRule();

        at.addRow("Duration: ", getDuration() !=null? getDuration(): "N/A");
        at.addRule();
        at.addRow("Programming Languages: ",
                getProgrammingLanguages() != null && !getProgrammingLanguages().isEmpty() ?
                        String.join(", ", getProgrammingLanguages()) : "N/A");
        at.addRule();

        return "\n" + at.render();
    }

    /**
     * Returns true if the software project is equal to another object.
     *
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
     *
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
     *
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
