package seedu.internsprint.model.userprofile.project;

import java.util.LinkedHashMap;
import java.util.Map;

import de.vandermeer.asciitable.AsciiTable;
import org.json.JSONObject;

import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_REQUIRED_PARAMETERS;

/**
 * Represents a general project.
 */
public class GeneralProject extends Project {
    private String department;

    public GeneralProject(String projectName, String role, String department) {
        super(projectName, role);
        if (department == null || department.isBlank()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/dept"));
        }
        this.department = department;
    }

    public GeneralProject(String projectName, String role, String department, String objectives, String description,
                          String duration) {
        super(projectName, role, objectives, description, duration);
        if (department == null || department.isBlank()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/dept"));
        }
        this.department = department;
    }

    /**
     * Creates a copy of the general project.
     *
     * @return Copy of the general project.
     */
    @Override
    public GeneralProject copy() {
        return new GeneralProject(projectName, role, department, objectives, description, duration);
    }

    /**
     * Returns a string representation of the general project.
     * Shows the project name, role, and department.
     *
     * @return String representation of the general project.
     */
    @Override
    public String toString() {
        return "Project: " + projectName + ", Role: " + role + ", Dept: " + department;
    }

    /**
     * Returns a string representation of the general project.
     * Shows all details of the general project.
     *
     * @return String representation of the general project.
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
        at.addRow("Department: ", getDepartment() !=null? getDepartment(): "N/A");
        at.addRule();

        return "\n" + at.render();
    }

    /**
     * Returns true if the general project is equal to another object.
     * Two general projects are equal if they have the same project name, role, and department.
     * This method overrides the equals method in the Object class.
     *
     * @param obj Object to compare to.
     * @return True if the general project is equal to the other object, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        GeneralProject generalProject = (GeneralProject) obj;
        return projectName.equals(generalProject.getProjectName())
                && role.equals(generalProject.getRole())
                && department.equals(generalProject.getDepartment());
    }

    /**
     * Returns a JSON object representing the general project.
     *
     * @return JSON object representing the general project.
     */
    @Override
    public JSONObject toJson() {
        Map<String, Object> orderedMap = new LinkedHashMap<>();
        orderedMap.put("type", "general");
        orderedMap.put("projectName", projectName);
        orderedMap.put("role", role);
        orderedMap.put("department", department);
        orderedMap.put("objectives", objectives);
        orderedMap.put("description", description);
        orderedMap.put("duration", duration);
        return new JSONObject(orderedMap);
    }

    /**
     * Returns a GeneralProject object from a JSON object.
     *
     * @param json JSON object representing the general project.
     * @return GeneralProject object.
     */
    public static GeneralProject fromJson(JSONObject json) {
        return new GeneralProject(
                json.getString("projectName"),
                json.getString("role"),
                json.getString("department"),
                json.optString("objectives", ""),
                json.optString("description", ""),
                json.optString("duration", "")
        );
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
