package seedu.internsprint.storage;

import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.userprofile.project.GeneralProject;
import seedu.internsprint.model.userprofile.project.HardwareProject;
import seedu.internsprint.model.userprofile.project.SoftwareProject;
import seedu.internsprint.model.userprofile.project.ProjectList;
import seedu.internsprint.util.InternSprintLogger;

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

import static seedu.internsprint.util.InternSprintExceptionMessages.FILE_ALREADY_EXISTS;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_CREATE_DIRECTORY;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_CREATE_FILE;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_READ_FILE;

import static seedu.internsprint.util.InternSprintMessages.LOADING_DATA_SUCCESS;
import static seedu.internsprint.util.InternSprintMessages.LOADING_DATA_FIRST_TIME;

/**
 * Handles the storage of project data.
 */
public class ProjectStorageHandler implements Storage<ProjectList> {
    public static final String FILE_PATH = Paths.get("data", "projects.txt").toString();
    private static File file;
    private static final Logger logger = InternSprintLogger.getLogger();

    public ProjectStorageHandler() {
        file = new File(FILE_PATH);
    }

    /**
     * Creates the file if it does not exist.
     */
    public void createFile() {
        try {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    throw new RuntimeException(String.format(UNABLE_TO_CREATE_DIRECTORY,
                        file.getParentFile().getAbsolutePath()));
                }
                assert file.getParentFile().exists() : "Directory should exist at this point";
            }
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new RuntimeException(String.format(FILE_ALREADY_EXISTS,
                        file.getAbsolutePath()));
                }
                assert file.exists() : "File should exist at this point";
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Unable to create file {0}", file.getAbsolutePath());
            throw new RuntimeException(String.format(UNABLE_TO_CREATE_FILE,
                    file.getAbsolutePath()));
        }
    }

    /**
     * Saves the projects to the file.
     *
     * @param projects List of projects to be saved.
     */
    public void save(ProjectList projects) throws IOException {
        logger.log(Level.INFO, "Saving Projects to file ...");
        JSONArray jsonArray = new JSONArray();
        projects.getProjectMap().forEach((type, list) -> {
            list.forEach(project -> jsonArray.put(project.toJson()));
        });

        if (!file.exists()) {
            createFile();
        }
        assert file.exists() : "File should exist at this point";

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(jsonArray.toString(4));
            logger.log(Level.INFO, String.format("Successfully saved %s Projects to file %s",
                jsonArray.length(), file.getAbsolutePath()));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to file {0}", file.getAbsolutePath());
            throw new IOException(String.format(UNABLE_TO_CREATE_FILE,
                file.getAbsolutePath()));
        }

    }

    /**
     * Loads the projects from the file.
     *
     * @param projects List of projects to be loaded.
     * @return CommandResult object indicating the success of the operation.
     */
    public CommandResult load(ProjectList projects) {
        logger.log(Level.INFO, "Beginning process to load projects from file ...");
        CommandResult result;
        if (!file.exists() || file.length() == 0) {
            logger.log(Level.INFO, "Data file loaded is empty currently");
            result = new CommandResult(LOADING_DATA_FIRST_TIME);
            result.setSuccessful(true);
            return result;
        }
        assert file.length() != 0 : "File should not be an empty file at this point";

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
            logger.log(Level.WARNING, "Error in formatting such that JSONArray could not be" +
                    " created successfully");
            result = errorReadingFile();
            return result;
        }
        assert !jsonArray.isEmpty() : "Array of JSON objects read from file should not be empty at this point";
        logger.log(Level.INFO, "Successfully extracted projects as JSON objects from file");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject projectJson = jsonArray.getJSONObject(i);
            addProjectToList(projects, projectJson);
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
        feedback.add(String.format(UNABLE_TO_READ_FILE, file.getAbsolutePath()));
        result = new CommandResult(feedback);
        result.setSuccessful(false);
        return result;
    }

    /**
     * Adds the project to the list of projects.
     *
     * @param projects    List of projects.
     * @param projectJson JSON object representing the project.
     */
    private static void addProjectToList(ProjectList projects, JSONObject projectJson) {
        switch (projectJson.getString("type")) {
        case "general":
            projects.addProject(GeneralProject.fromJson(projectJson));
            break;
        case "software":
            projects.addProject(SoftwareProject.fromJson(projectJson));
            break;
        case "hardware":
            projects.addProject(HardwareProject.fromJson(projectJson));
            break;
        default:
            break;
        }
    }
}
