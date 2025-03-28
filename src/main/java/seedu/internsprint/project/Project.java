package seedu.internsprint.project;

import org.json.JSONObject;

import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_REQUIRED_PARAMETERS;

/**
 * Represents a project.
 */
public abstract class Project {

    protected String projectName;
    protected String role;
    protected String objectives;
    protected String description;
    protected String duration;


    public Project(String projectName, String role, String objectives, String description, String duration) {
        if (projectName == null || role == null || projectName.isBlank() || role.isBlank()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/n or /r or /dur or /desc" +
                    " or /obj"));
        }
        this.projectName = projectName;
        this.role = role;
        this.objectives = objectives;
        this.description = description;
        this.duration = duration;
    }

    /**
     * Returns a string representation of the project.
     *
     * @return String representation of the project.
     */
    @Override
    public abstract String toString();

    /**
     * Returns a string representation of the project.
     * Shows all details of the project.
     *
     * @return String representation of the project.
     */

    public abstract String toDescription();

    /**
     * Creates a copy of the project.
     *
     * @return Copy of the project.
     */
    public abstract Project copy();

    /**
     * Returns true if the project is equal to another object.
     *
     * @param obj Object to compare with.
     * @return True if the project is equal to the object.
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * Converts the project to a JSON object.
     *
     * @return JSON object of the project.
     */
    public abstract JSONObject toJson();

    public abstract String getType();

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getRole() {
        return role;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
