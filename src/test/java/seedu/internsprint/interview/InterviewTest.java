package seedu.internsprint.interview;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InterviewTest {
    private Interview interviewBasic;
    private Interview interviewFull;

    @BeforeEach
    void setUp() {
        interviewBasic = new Interview("2025-07-01", "10:00", "11:00",
                "Technical");
        interviewFull = new Interview("2025-08-02", "14:00", "15:00",
                "Coding Test", "hr@123.com", "Prepare C++ script");
    }

    @Test
    void constructor_basicRequiredFields_shouldParseAllCompulsaryFields() {
        assertEquals(LocalDate.of(2025, 7, 1), interviewBasic.getInterviewDateAsLocalDate());
        assertEquals(LocalTime.of(10, 0), interviewBasic.getInterviewStartTimeAsLocalTime());
        assertEquals(LocalTime.of(11, 0), interviewBasic.getInterviewEndTimeAsLocalTime());
        assertEquals("Technical", interviewBasic.getInterviewType());
        assertNull(interviewBasic.getInterviewerEmail());
        assertNull(interviewBasic.getNotes());
    }

    @Test
    void constructor_nullDateInbasicRequiredFields_doesNotParseAllCompulsaryFields() {
        assertThrows(IllegalArgumentException.class, () ->
                new Interview(null, "10:00", "11:00",
                        "Technical"));
    }

    @Test
    void constructor_blankStartTimeInbasicRequiredFields_doesNotParseAllCompulsaryFields() {
        assertThrows(IllegalArgumentException.class, () ->
                new Interview("2025-07-01", "", "11:00",
                        "technical"));
    }

    @Test
    void constructor_fullRequiredFields_shouldParseAllOptionalFields() {
        assertEquals(LocalDate.of(2025, 8, 2), interviewFull.getInterviewDateAsLocalDate());
        assertEquals(LocalTime.of(14, 0), interviewFull.getInterviewStartTimeAsLocalTime());
        assertEquals(LocalTime.of(15, 0), interviewFull.getInterviewEndTimeAsLocalTime());
        assertEquals("Coding Test", interviewFull.getInterviewType());
        assertEquals("hr@123.com", interviewFull.getInterviewerEmail());
        assertEquals("Prepare C++ script", interviewFull.getNotes());
    }

    @Test
    void constructor_nullEndTimeInFullStartTimeInbasicRequiredFields_doesNotParseAllCompulsaryFields() {
        assertThrows(IllegalArgumentException.class, () ->
                new Interview("2025-08-02", "14:00", null,
                        "Coding Test", "hr@123.com", "Prepare C++ script"));
    }

    @Test
    void constructor_blankDateInFullStartTimeInbasicRequiredFields_doesNotParseAllCompulsaryFields() {
        assertThrows(IllegalArgumentException.class, () ->
                new Interview("", "14:00", null,
                        "Coding Test", "hr@123.com", "Prepare C++ script"));
    }

    @Test
    void addInterviewRound_shouldIncreaseRoundsAndStoreCorrectly() {
        Interview round2 = new Interview("2025-06-05", "09:00", "10:00",
                "CEO Test");
        Interview round3 = new Interview("2025-06-06", "09:00", "10:00",
                "Final");
        interviewBasic.addInterviewRound(round2);
        interviewBasic.addInterviewRound(round3);

        assertEquals(2, interviewBasic.getNextRounds().size());
        assertEquals("Final", interviewBasic.getNextRounds().get(1).getInterviewType());
    }

    @Test
    void addInterviewRound_nullInterview_shouldNotAddToList() {
        int originalSize = interviewBasic.getNextRounds().size();
        interviewBasic.addInterviewRound(null);
        assertEquals(originalSize, interviewBasic.getNextRounds().size());
    }

    @Test
    void toString_shouldContainExpectedInformation() {
        String result = interviewFull.toString();
        assertTrue(result.contains("2025-08-02"));
        assertTrue(result.contains("14:00"));
        assertTrue(result.contains("15:00"));
        assertTrue(result.contains("Coding Test"));
        assertTrue(result.contains("hr@123.com"));
        assertTrue(result.contains("Prepare C++ script"));
    }

    @Test
    void toString_missingOptionalFields_shouldStillWork() {
        String result = interviewBasic.toString();
        assertTrue(result.contains("2025-07-01"));
        assertTrue(result.contains("10:00"));
        assertTrue(result.contains("11:00"));
        assertTrue(result.contains("Technical"));
        assertFalse(result.contains("Email"));
        assertFalse(result.contains("Notes"));
    }
}
