package seedu.internsprint.model.internship;

import seedu.internsprint.exceptions.DuplicateEntryException;
import seedu.internsprint.model.internship.interview.Interview;
import seedu.internsprint.storage.InternshipStorageHandler;
import seedu.internsprint.storage.StorageManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static seedu.internsprint.util.InternSprintExceptionMessages.DUPLICATE_INTERNSHIP;
import static seedu.internsprint.util.InternSprintExceptionMessages.UNABLE_TO_WRITE_FILE;

/**
 * Represents the list of internships.
 */
public class InternshipList {
    protected final HashMap<String, ArrayList<Internship>> internshipMap = new HashMap<>();
    protected int internshipCount = 0;
    private final StorageManager storageManager = StorageManager.getInstance();

    public InternshipList( ) {
        internshipMap.put("software", new ArrayList<>());
        internshipMap.put("hardware", new ArrayList<>());
        internshipMap.put("general", new ArrayList<>());
    }

    /**
     * Adds an internship to the list.
     *
     * @param internship Internship to be added.
     */
    public void addInternship(Internship internship) throws DuplicateEntryException {
        String type = internship.getType();
        if (internshipMap.get(type).contains(internship)) {
            throw new DuplicateEntryException(DUPLICATE_INTERNSHIP);
        }
        internship.setInternshipId(internshipCount);
        internshipMap.get(type).add(internship);
        internshipCount++;
        assert contains(internship) : "Internship should be in the list";
        assert internshipCount > 0 : "At least one internship should be in the list";
    }

    /**
     * Assigns an interview to the internship.
     *
     * @param interview Interview to be assigned.
     */
    public void addInterview(Interview interview) {
        int internshipId = interview.getInternshipId();
        getInternshipById(internshipId).setInterview(interview);
    }

    /**
     * Deletes an internship from the list.
     *
     * @param type Type of internship.
     * @param index Index of internship to be deleted.
     */
    public void deleteInternship(String type, int index) {
        internshipMap.get(type).remove(index);
        internshipCount--;
        assert internshipCount >= 0 : "Internship count should not be negative";
    }

    /**
     * Checks if the list contains the internship.
     *
     * @param internship Internship to be checked.
     * @return True if the internship is in the list, false otherwise.
     */
    public boolean contains(Internship internship) {
        String type = internship.getType();
        return internshipMap.get(type).contains(internship);
    }

    /**
     * Saves the internships to the storage.
     */
    public void saveInternships() throws IOException {
        try {
            storageManager.saveInternshipData(this);
            storageManager.saveInterviewData(this);
        } catch (IOException e) {
            throw new IOException(String.format(UNABLE_TO_WRITE_FILE, InternshipStorageHandler.FILE_PATH));
        }
    }

    /**
     * Gets the internship by its ID.
     *
     * @param internshipId ID of the internship.
     * @return Internship with the given ID.
     */
    public Internship getInternshipById(int internshipId) {
        for (ArrayList<Internship> internships : internshipMap.values()) {
            for (Internship internship : internships) {
                if (internship.getInternshipId() == internshipId) {
                    return internship;
                }
            }
        }
        return null;
    }

    /**
     * Gets the interview list of all internships.
     *
     * @return List of interviews.
     */
    public ArrayList<Interview> getInterviewList() {
        ArrayList<Interview> interviewList = new ArrayList<>();
        for (ArrayList<Internship> internships : internshipMap.values()) {
            for (Internship internship : internships) {
                if (internship.getInterview() != null) {
                    interviewList.add(internship.getInterview());
                }
            }
        }
        return interviewList;
    }

    public HashMap<String, ArrayList<Internship>> getInternshipMap() {
        return internshipMap;
    }

    public int getInternshipCount() {
        return internshipCount;
    }
}
