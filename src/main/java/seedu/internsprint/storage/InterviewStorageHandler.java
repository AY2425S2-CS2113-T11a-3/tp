package seedu.internsprint.storage;

import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.internship.interview.Interview;
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

public class InterviewStorageHandler implements Storage<InternshipList> {

    public static final String FILE_PATH = Paths.get("data", "interviews.txt").toString();
    private static File file;
    private static final Logger logger = InternSprintLogger.getLogger();

    public InterviewStorageHandler() {
        file = new File(FILE_PATH);
    }

    /**
     * Creates the file if it does not exist.
     */
    @Override
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
     * Saves the interviews to the file.
     *
     * @param internships List of internships to be saved.
     */
    @Override
    public void save(InternshipList internships) throws IOException {
        logger.log(Level.INFO, "Saving Interviews to file ...");
        JSONArray jsonArray = new JSONArray();
        internships.getInterviewList().forEach(interview -> jsonArray.put(interview.toJson()));

        if (!file.exists()) {
            createFile();
        }
        assert file.exists() : "File should exist at this point";

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(jsonArray.toString(4));
            logger.log(Level.INFO, String.format("Successfully saved %s Interviews to file %s",
                jsonArray.length(), file.getAbsolutePath()));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to file");
            throw new IOException(String.format(UNABLE_TO_CREATE_FILE,
                    file.getAbsolutePath()));
        }

    }

    /**
     * Loads the interviews from the file.
     *
     * @param internships List of internships where interviews need to be loaded.
     * @return CommandResult object indicating the success of the operation.
     */
    @Override
    public CommandResult load(InternshipList internships) {
        logger.log(Level.INFO, "Beginning process to load interviews from file ...");
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
        if (jsonArray.isEmpty() && jsonData.length() != 2) {
            logger.log(Level.WARNING, "Error in formatting such that JSONArray could not be" +
                    " created successfully");
            result = errorReadingFile();
            return result;
        }
        assert !(jsonArray.isEmpty() && jsonData.length() != 2) :
            "Array of JSON objects read from file should not be empty at this point";
        logger.log(Level.INFO, "Successfully extracted interviews as JSON objects from file");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject interviewJson = jsonArray.getJSONObject(i);
            addInterviewToList(internships, interviewJson);
        }
        logger.log(Level.INFO, "Successfully added interviews from file to interview list in app");
        result = new CommandResult(LOADING_DATA_SUCCESS);
        result.setSuccessful(true);
        return result;
    }

    /**
     * Adds the interview to the list of interviews.
     *
     * @param internships List of internships.
     * @param interviewJson JSON object representing the interview.
     */
    private static void addInterviewToList(InternshipList internships, JSONObject interviewJson) {
        Interview interview = Interview.fromJson(interviewJson);
        internships.addInterview(interview);
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
}
