package seedu.internsprint.project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_REQUIRED_PARAMETERS;

/**
 * Represents a hardware project.
 */
public class HardwareProject extends Project {
    private List<String> hardwareComponents;

    public HardwareProject(String projectName, String role, List<String> hardwareComponents) {
        super(projectName, role);
        if (hardwareComponents == null || hardwareComponents.isEmpty()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/tech"));
        }
        for (String component : hardwareComponents) {
            if (component == null || component.isBlank()) {
                throw new IllegalArgumentException("Hardware component names must not be null or blank.");
            }
        }
        this.hardwareComponents = hardwareComponents;
    }

    public HardwareProject(String projectName, String role, List<String> hardwareComponents, String objectives,
                           String description, String duration) {
        super(projectName, role, objectives, description, duration);
        if (hardwareComponents == null || hardwareComponents.isEmpty()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/tech"));
        }
        this.hardwareComponents = hardwareComponents;
    }

    /**
     * Creates a copy of the hardware project.
     * @return Copy of the hardware project.
     */
    @Override
    public HardwareProject copy() {
        return new HardwareProject(projectName, role, hardwareComponents,
            objectives, description, duration);
    }

    /**
     * Returns a string representation of the hardware project.
     * @return String representation of the hardware project.
     */
    @Override
    public String toString() {
        return "Project: " + projectName + ", Role: " + role +
            ", Hardware Components: " + String.join(", ", hardwareComponents);
    }

    /**
     * Returns a string representation of the hardware project.
     * Shows all details of the hardware project.
     * @return String representation of the hardware project.
     */
    @Override
    public ArrayList<String> toDescription() {
        ArrayList<String> projectString = super.toDescription();
        projectString.add("Hardware Components: " + String.join(", ", hardwareComponents));
        return projectString;
    }

    /**
     * Returns true if the hardware project is equal to another object.
     * @param obj Object to compare to.
     * @return True if the hardware project is equal to the object, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        HardwareProject hardwareProject = (HardwareProject) obj;
        return projectName.equals(hardwareProject.getProjectName())
                && role.equals(hardwareProject.getRole())
                && hardwareComponents.equals(hardwareProject.getHardwareComponents());
    }

    /**
     * Converts the hardware project to a JSON object.
     * @return JSON object representing the hardware project.
     */
    @Override
    public JSONObject toJson() {
        Map<String, Object> orderedMap = new LinkedHashMap<>();
        orderedMap.put("type", "hardware");
        orderedMap.put("projectName", projectName);
        orderedMap.put("role", role);
        orderedMap.put("hardwareComponents", hardwareComponents);
        orderedMap.put("objectives", objectives);
        orderedMap.put("description", description);
        orderedMap.put("duration", duration);
        return new JSONObject(orderedMap);
    }

    @Override
    public String getType() {
        return "hardware";
    }

    public List<String> getHardwareComponents() {
        return hardwareComponents;
    }

    public void setHardwareComponents(List<String> hardwareComponents) {
        this.hardwareComponents = hardwareComponents;
    }

    /**
     * Returns a HardwareProject object from a JSON object.
     * @param json JSON object representing the hardware project.
     * @return HardwareProject object.
     */
    public static HardwareProject fromJson(JSONObject json) {
        List<String> hardwareComponents = new ArrayList<>();
        json.getJSONArray("hardwareComponents").forEach(item -> {
            if (item instanceof String) {
                hardwareComponents.add((String) item);
            }
        });
        return new HardwareProject(
                json.getString("projectName"),
                json.getString("role"),
                hardwareComponents,
                json.optString("objectives", ""),
                json.optString("description", ""),
                json.optString("duration", "")
        );
    }
}
