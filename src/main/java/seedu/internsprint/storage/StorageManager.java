package seedu.internsprint.storage;

import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;
import seedu.internsprint.model.userprofile.project.ProjectList;

import java.io.IOException;

/**
 * Manages the storage of data in the application.
 * <p>
 * This class is a singleton, meaning that there is only one instance of it in the application.
 * It is responsible for creating and managing the storage handlers for different types of data.
 */
public class StorageManager {

    private static StorageManager storageManager;
    private static boolean isConfigured = false;

    private final InternshipStorageHandler internshipStorageHandler;
    private final ProjectStorageHandler projectStorageHandler;
    private final ProfileStorageHandler profileStorageHandler;
    private final InterviewStorageHandler interviewStorageHandler;

    private StorageManager() {
        this.internshipStorageHandler = new InternshipStorageHandler();
        this.projectStorageHandler = new ProjectStorageHandler();
        this.profileStorageHandler = new ProfileStorageHandler();
        this.interviewStorageHandler = new InterviewStorageHandler();
        isConfigured = true;
    }

    public static StorageManager getInstance() {
        if (!isConfigured) {
            storageManager = new StorageManager();
        }
        return storageManager;
    }

    // ================= Internship methods =================

    public void saveInternshipData(InternshipList internships) throws IOException {
        internshipStorageHandler.save(internships);
    }

    public CommandResult loadInternshipData(InternshipList internships) {
        return internshipStorageHandler.load(internships);
    }

    // ================= Interview methods =================

    public void saveInterviewData(InternshipList internships) throws IOException {
        interviewStorageHandler.save(internships);
    }

    public CommandResult loadInterviewData(InternshipList internships) {
        return interviewStorageHandler.load(internships);
    }

    // ================== UserProfile methods =================

    public void saveUserProfileData(UserProfile userProfile) throws IOException {
        profileStorageHandler.save(userProfile);
    }

    public CommandResult loadUserProfileData(UserProfile userProfile) {
        return profileStorageHandler.load(userProfile);
    }
    // ================== Project methods =================

    public void saveProjectData(ProjectList projects) throws IOException {
        projectStorageHandler.save(projects);
    }

    public CommandResult loadProjectData(ProjectList projects) {
        return projectStorageHandler.load(projects);
    }
}
