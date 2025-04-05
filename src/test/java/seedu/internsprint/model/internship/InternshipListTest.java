package seedu.internsprint.model.internship;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InternshipListTest {

    private InternshipList internshipList;
    private Internship generalInternship;
    private Internship softwareInternship;
    private Internship hardwareInternship;

    @BeforeEach
    void setUp() {
        generalInternship = new GeneralInternship("Google","Human Resource", "HR");
        softwareInternship = new SoftwareInternship("Facebook","Software Engineering", "SWE");
        hardwareInternship = new HardwareInternship("Apple","Hardware Engineering", "HWE");
        internshipList = new InternshipList();
    }

    @Test
    void addInternship() {
        assertDoesNotThrow(() -> internshipList.addInternship(generalInternship));
        assertEquals(1, internshipList.getInternshipCount());
        assertDoesNotThrow(() -> internshipList.addInternship(softwareInternship));
        assertEquals(2, internshipList.getInternshipCount());
        assertDoesNotThrow(() -> internshipList.addInternship(hardwareInternship));
        assertEquals(3, internshipList.getInternshipCount());
    }

    @Test
    void contains_internshipAlreadyInList_returnsTrue() {
        assertDoesNotThrow(() -> internshipList.addInternship(generalInternship));
        assertTrue(internshipList.contains(generalInternship));
        assertFalse(internshipList.contains(softwareInternship));
        assertFalse(internshipList.contains(hardwareInternship));
    }

    @Test
    void getInternshipMap() {
        assertDoesNotThrow(() -> internshipList.addInternship(generalInternship));
        assertDoesNotThrow(() -> internshipList.addInternship(softwareInternship));
        assertDoesNotThrow(() -> internshipList.addInternship(hardwareInternship));
        HashMap<String, ArrayList<Internship>> internshipMap = internshipList.getInternshipMap();

        HashMap<String, ArrayList<Internship>> expectedMap = new HashMap<>();
        expectedMap.put("software", new ArrayList<>());
        expectedMap.get("software").add(softwareInternship);
        expectedMap.put("hardware", new ArrayList<>());
        expectedMap.get("hardware").add(hardwareInternship);
        expectedMap.put("general", new ArrayList<>());
        expectedMap.get("general").add(generalInternship);

        assertEquals(expectedMap, internshipMap);
    }
}
