package seedu.internsprint.internship;

import java.util.ArrayList;
import java.util.HashMap;

public class InternshipList {
    protected final HashMap<String, ArrayList<Internship>> internshipMap = new HashMap<>();
    protected int internshipCount = 0;

    public InternshipList() {
        //internshipMap = new HashMap<>();
        internshipMap.put("software", new ArrayList<>());
        internshipMap.put("hardware", new ArrayList<>());
        internshipMap.put("general", new ArrayList<>());
    }

    public HashMap<String, ArrayList<Internship>> getInternshipMap() {
        return internshipMap;
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

    public int getInternshipCount() {
        return internshipCount;
    }
}
