package seedu.internsprint.handler;

import seedu.internsprint.internship.GeneralInternship;
import seedu.internsprint.internship.HardwareInternship;
import seedu.internsprint.internship.SoftwareInternship;
import seedu.internsprint.internship.InternshipList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import static seedu.internsprint.util.InternSprintExceptionMessages.FILE_ALREADY_EXISTS;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_CREATE_DIRECTORY;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_CREATE_FILE;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_WRITE_FILE;

public class StorageHandler {
    private static final String FILE_PATH = Paths.get("data", "internships.txt").toString();
    private static File file;

    public StorageHandler() {
        file = new File(FILE_PATH);
    }

    public void createFile() {
        try {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    throw new RuntimeException(String.format(UNABLE_TO_CREATE_DIRECTORY,
                            file.getParentFile().getAbsolutePath()));
                }
                if (!file.createNewFile()) {
                    throw new RuntimeException(String.format(FILE_ALREADY_EXISTS,
                            file.getAbsolutePath()));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format(UNABLE_TO_CREATE_FILE,
                    file.getAbsolutePath()));
        }
    }

    public void saveInternships(InternshipList internships) {
        JSONArray jsonArray = new JSONArray();
        internships.getInternshipMap().forEach((type, list) -> {
            list.forEach(internship -> jsonArray.put(internship.toJson()));
        });

        if (!file.exists()) {
            createFile();
        }
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(jsonArray.toString(4));
        } catch (IOException e) {
            throw new RuntimeException(String.format(UNABLE_TO_WRITE_FILE,
                    file.getAbsolutePath()));
        }
    }

    public static void loadInternships(InternshipList internships) {
        if (!file.exists()) {
            return;
        }
        StringBuilder jsonData = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("Unable to read file: %s", file.getAbsolutePath()));
        }

        JSONArray jsonArray = new JSONArray(jsonData.toString());
        if (jsonArray.isEmpty()) {
            return;
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject internshipJson = jsonArray.getJSONObject(i);
            switch (internshipJson.getString("type")) {
            case "general":
                internships.addInternship(GeneralInternship.fromJson(internshipJson));
                break;
            case "software":
                internships.addInternship(SoftwareInternship.fromJson(internshipJson));
                break;
            case "hardware":
                internships.addInternship(HardwareInternship.fromJson(internshipJson));
                break;
            default:
                break;
            }
        }
    }
}


