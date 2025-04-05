package seedu.internsprint.model.internship.interview;

import seedu.internsprint.exceptions.DuplicateEntryException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
        assertEquals(LocalDate.of(2025, 7, 1), interviewBasic.getUnformattedInterviewDate());
        assertEquals(LocalTime.of(10, 0), interviewBasic.getUnformattedInterviewStartTime());
        assertEquals(LocalTime.of(11, 0), interviewBasic.getUnformattedInterviewEndTime());
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
        assertEquals(LocalDate.of(2025, 8, 2), interviewFull.getUnformattedInterviewDate());
        assertEquals(LocalTime.of(14, 0), interviewFull.getUnformattedInterviewStartTime());
        assertEquals(LocalTime.of(15, 0), interviewFull.getUnformattedInterviewEndTime());
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
    void constructor_provideEndTimeBeforeStartTime_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
            new Interview("tomorrow", "10am", "9am", "Technical"));
    }

    @Test
    void addInterviewRound_shouldIncreaseRoundsAndStoreCorrectly() {
        Interview round2 = new Interview("2025-06-05", "09:00", "10:00",
                "CEO Test");
        Interview round3 = new Interview("2025-06-06", "09:00", "10:00",
                "Final");
        assertDoesNotThrow(() -> interviewBasic.addInterviewRound(round2));
        assertDoesNotThrow(() -> interviewBasic.addInterviewRound(round3));

        assertEquals(2, interviewBasic.getNextRounds().size());
        assertEquals("Final", interviewBasic.getNextRounds().get(1).getInterviewType());
    }

    @Test
    void addInterviewRound_nullInterview_shouldNotAddToList() {
        int originalSize = interviewBasic.getNextRounds().size();
        assertDoesNotThrow(() -> interviewBasic.addInterviewRound(null));
        assertEquals(originalSize, interviewBasic.getNextRounds().size());
    }

    @Test
    void addInterviewRound_provideDuplicateInterview_throwsDuplicateEntryException() {
        Interview mainInterview = new Interview("2023-10-01", "10:00", "11:00", "Technical");
        Interview duplicateRound = new Interview("2023-10-01", "10:00", "11:00", "Verbal");

        assertThrows(DuplicateEntryException.class, () -> mainInterview.addInterviewRound(duplicateRound));
    }

    @Test
    void addInterviewRound_provideDuplicateRound_throwsDuplicateEntryException() {
        Interview mainInterview = new Interview("2023-10-01", "10:00", "11:00", "Technical");
        Interview firstRound = new Interview("2023-10-01", "12:00", "13:00", "Verbal");
        Interview duplicateRound = new Interview("2023-10-01", "12:00", "13:00", "Verbal");
        assertDoesNotThrow(() -> mainInterview.addInterviewRound(firstRound));
        assertThrows(DuplicateEntryException.class, () -> mainInterview.addInterviewRound(duplicateRound));
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

    @Test
    void testEquals_provideMatchingMainInterview_returnsTrue() {
        Interview interview1 = new Interview("2023-10-01", "10:00", "11:00", "Technical");
        Interview interview2 = new Interview("2023-10-01", "10:00", "11:00", "Verbal");
        assertTrue(interview1.equals(interview2));
    }

    @Test
    void testEquals_provideDifferentMainInterview_returnsFalse() {
        Interview interview1 = new Interview("2023-10-01", "10:00", "11:00", "Technical");
        Interview interview2 = new Interview("2023-10-02", "10:00", "11:00", "Technical");
        assertFalse(interview1.equals(interview2));
    }
}
