package seedu.internsprint.internship;

import seedu.internsprint.handler.StorageHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class InternshipList {
    protected final HashMap<String, ArrayList<Internship>> internshipMap = new HashMap<>();
    protected int internshipCount = 0;
    private final StorageHandler storageHandler = new StorageHandler();

    public InternshipList() {
        //internshipMap = new HashMap<>();
        internshipMap.put("software", new ArrayList<>());
        internshipMap.put("hardware", new ArrayList<>());
        internshipMap.put("general", new ArrayList<>());
    }

    public void addInternship(Internship internship) {
        String type = internship.getType();
        internshipMap.get(type).add(internship);
        internshipCount++;
    }

    public boolean contains(Internship internship) {
        String type = internship.getType();
        return internshipMap.get(type).contains(internship);
    }

    public void saveInternships() {
        storageHandler.saveInternships(this);
    }

    public int getInternshipCount() {
        return internshipCount;
    }

    public HashMap<String, ArrayList<Internship>> getInternshipMap() {
        return internshipMap;
    }
}
