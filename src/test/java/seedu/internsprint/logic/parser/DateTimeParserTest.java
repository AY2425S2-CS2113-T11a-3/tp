package seedu.internsprint.logic.parser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateTimeParserTest {

    @Test
    void parseDateTimeInput_provideValidDateTime_returnsCorrectObject() {
        assertEquals("2021-08-01T00:00", DateTimeParser.parseDateTimeInput("2021-08-01 00:00").toString());
        assertEquals("2021-08-01T17:00", DateTimeParser.parseDateTimeInput("2021-08-01 5:00pm").toString());
        assertEquals("2021-08-01T17:00", DateTimeParser.parseDateTimeInput("2021-08-01 5PM").toString());
    }

    @Test
    void parseDateInput_provideValidDate_returnsCorrectObject() {
        assertEquals("2021-08-01", DateTimeParser.parseDateInput("2021-08-01").toString());
        assertEquals("2021-08-01", DateTimeParser.parseDateInput("2021/08/01").toString());
    }

    @Test
    void parseTimeInput_provideValidTime_returnsCorrectObject() {
        assertEquals("00:00", DateTimeParser.parseTimeInput("00:00").toString());
        assertEquals("17:00", DateTimeParser.parseTimeInput("5:00pm").toString());
        assertEquals("17:00", DateTimeParser.parseTimeInput("5PM").toString());
    }

    @Test
    void parseTimeInput_provideHypenatedTime_returnsCorrectObject() {
        assertEquals("11:00", DateTimeParser.parseTimeInput("11-00").toString());
        assertEquals("17:00", DateTimeParser.parseTimeInput("5-00pm").toString());
        assertEquals("17:00", DateTimeParser.parseTimeInput("5-PM").toString());
    }

    @Test
    void parseDateTimeInput_provideNaturalLanguageDateTime_returnsCorrectObject() {
        LocalDate today = LocalDate.now();
        assertEquals(today.plusDays(1).atTime(17, 0).toString(),
            DateTimeParser.parseDateTimeInput("tomorrow 5pm").toString());

        assertEquals(today.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)).atTime(9, 0).toString(),
            DateTimeParser.parseDateTimeInput("coming friday 9am").toString());

        assertEquals(today.withDayOfMonth(12).plusMonths(1).atTime(15, 0).toString(),
            DateTimeParser.parseDateTimeInput("12th of next month 3pm").toString());

        assertEquals(today.plusWeeks(2).atTime(17, 0).toString(),
            DateTimeParser.parseDateTimeInput("2 weeks from now 5pm").toString());
    }

    @Test
    void parseDateInput_provideNaturalLanguageDate_returnsCorrectObject() {
        LocalDate today = LocalDate.now();
        assertEquals(today.plusDays(1).toString(),
            DateTimeParser.parseDateInput("tomorrow").toString());

        assertEquals(today.withDayOfMonth(12).toString(),
            DateTimeParser.parseDateInput("12th of this month").toString());

        assertEquals(today.withDayOfMonth(12).plusMonths(3).toString(),
            DateTimeParser.parseDateInput("12th of three months").toString());
    }

    @Test
    void parseTimeInput_provideNaturalLanguageTime_returnsCorrectObject() {
        assertEquals("09:00", DateTimeParser.parseTimeInput("9 am").toString());
        assertEquals("12:00", DateTimeParser.parseTimeInput("12 noon").toString());
        assertEquals("00:00", DateTimeParser.parseTimeInput("midnight").toString());
    }

    @Test
    void parseDateTimeInput_provideEmptyDate_throwsException() {
        assertThrows(IllegalArgumentException.class,
            () -> DateTimeParser.parseDateTimeInput(""));
        assertThrows(IllegalArgumentException.class,
            () -> DateTimeParser.parseDateTimeInput("this is random text"));
    }

    @Test
    void parseDateTimeInput_provideInvalidDate_returnsNull() {
        assertThrows(IllegalArgumentException.class,
            () -> DateTimeParser.parseDateTimeInput("next Smon 5pm"));
        assertThrows(IllegalArgumentException.class,
            () -> DateTimeParser.parseDateInput("12th of three months later"));
    }
}
