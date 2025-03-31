package seedu.internsprint.model.userprofile.project;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the list of projects.
 */
public class ProjectList {
    protected final HashMap<String, ArrayList<Project>> projectMap = new HashMap<>();
    protected int projectCount = 0;

    public ProjectList() {
        projectMap.put("software", new ArrayList<>());
        projectMap.put("hardware", new ArrayList<>());
        projectMap.put("general", new ArrayList<>());
    }

    /**
     * Adds a project to the list.
     *
     * @param project Project to be added.
     */
    public void addProject(Project project) {
        String type = project.getType();
        projectMap.get(type).add(project);
        projectCount++;
        assert contains(project) : "Project should be in the list";
        assert projectCount > 0 : "At least one project should be in the list";
    }

    /**
     * Checks if the list contains the project.
     *
     * @param project Project to be checked.
     * @return True if the project is in the list, false otherwise.
     */
    public boolean contains(Project project) {
        String type = project.getType();
        return projectMap.get(type).contains(project);
    }

    public HashMap<String, ArrayList<Project>> getProjectMap() {
        return projectMap;
    }

    public int getProjectCount() {
        return projectCount;
    }
}
