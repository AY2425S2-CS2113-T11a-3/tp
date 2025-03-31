package seedu.internsprint.storage;

import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.userprofile.project.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_CREATE_DIRECTORY;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_WRITE_FILE;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_CREATE_FILE;
import static seedu.internsprint.util.InternSprintExceptionMessages.FILE_ALREADY_EXISTS;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_READ_FILE;
import static seedu.internsprint.util.InternSprintMessages.LOADING_DATA_FIRST_TIME;
import static seedu.internsprint.util.InternSprintMessages.LOADING_DATA_SUCCESS;

/**
 * Handles the reading and writing of project data to and from the file.
 */

public class ProjectStorageHandler {
    private static final String FILE_PATH = Paths.get("data", "projects.txt").toString();
    private static File file;
    private static Logger logger = Logger.getLogger(ProfileStorageHandler.class.getName());

    public ProjectStorageHandler() {
        file = new File(FILE_PATH);
    }

    /**
     * Creates the file if it does not exist.
     */
    public static void createFile() {
        try {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    throw new RuntimeException(String.format(UNABLE_TO_CREATE_DIRECTORY,
                            file.getParentFile().getAbsolutePath()));
                }
                assert file.getParentFile().exists() : "Directory should exist at this point";

                if (!file.createNewFile()) {
                    throw new RuntimeException(String.format(FILE_ALREADY_EXISTS,
                            file.getAbsolutePath()));
                }
                assert file.exists() : "File should exist at this point";
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format(UNABLE_TO_CREATE_FILE,
                    file.getAbsolutePath()));
        }
    }

    /**
     * Saves the projects to the file.
     *
     * @param projectList List of projects to be saved.
     */
    public void saveProjects(ProjectList projectList) {
        logger.log(Level.INFO, "Saving Projects to file ...");
        JSONArray jsonArray = new JSONArray();
        projectList.getProjectMap().forEach((type, list) -> {
            list.forEach(project -> jsonArray.put(project.toJson()));
        });
        if (!file.exists()) {
            createFile();
        }
        assert file.exists() : "File should exist at this point";

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(jsonArray.toString(4));
        } catch (IOException e) {
            throw new RuntimeException(String.format(UNABLE_TO_WRITE_FILE,
                    file.getAbsolutePath()));
        }
    }

    /**
     * Loads the projects from the file.
     *
     * @param projectList List of projects to be loaded.
     * @return CommandResult object indicating the success of the operation.
     */
    public static CommandResult loadProjects(ProjectList projectList) {
        logger.log(Level.INFO, "Beginning process to load projects from file ...");
        CommandResult result;
        if (!file.exists() || file.length() == 0) {
            result = new CommandResult(LOADING_DATA_FIRST_TIME);
            result.setSuccessful(true);
            return result;
        }

        StringBuilder jsonData = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
        } catch (IOException e) {
            result = errorReadingFile();
            logger.log(Level.SEVERE, "Error reading file");
            return result;
        }
        JSONArray jsonArray = new JSONArray(jsonData.toString());
        if (jsonArray.isEmpty()) {
            result = errorReadingFile();
            return result;
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject projectJson = jsonArray.getJSONObject(i);
            addProjectToList(projectList, projectJson);
        }
        logger.log(Level.INFO, "Successfully added projects from file to project list in app");
        result = new CommandResult(LOADING_DATA_SUCCESS);
        result.setSuccessful(true);
        return result;
    }

    /**
     * Returns a CommandResult object indicating that there was an error reading the file.
     *
     * @return CommandResult object indicating the error.
     */
    private static CommandResult errorReadingFile() {
        CommandResult result;
        List<String> feedback = new ArrayList<>();
        feedback.add(UNABLE_TO_READ_FILE);
        result = new CommandResult(feedback);
        result.setSuccessful(false);
        return result;
    }

    /**
     * Adds the project to the list of projects.
     *
     * @param projectList List of projects.
     * @param projectJson JSON object representing the project.
     */
    private static void addProjectToList(ProjectList projectList, JSONObject projectJson) {
        String type = projectJson.getString("type");
        switch (type) {
            case "hardware":
                projectList.addProject(HardwareProject.fromJson(projectJson));
                break;
            case "software":
                projectList.addProject(SoftwareProject.fromJson(projectJson));
                break;
            case "general":
                projectList.addProject(GeneralProject.fromJson(projectJson));
                break;
            default:
                throw new IllegalArgumentException("Unknown project type: " + type);
        }
    }
}
