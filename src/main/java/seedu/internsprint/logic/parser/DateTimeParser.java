package seedu.internsprint.logic.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.ocpsoft.prettytime.PrettyTime;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

import static seedu.internsprint.util.InternSprintExceptionMessages.INVALID_DATE_FORMAT;
import static seedu.internsprint.util.InternSprintExceptionMessages.PARTIAL_DATE_FORMAT;

/**
 * Parses date and time input strings into LocalDateTime, LocalDate, and LocalTime objects.
 */
public class DateTimeParser {

    /**
     * Parses a date and time input string into a LocalDateTime object.
     *
     * @param input The date and time input string.
     * @return The LocalDateTime object.
     */
    public static LocalDateTime parseDateTimeInput(String input) {
        Date date = extractDate(input);
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Parses a date input string into a LocalDate object.
     *
     * @param input The date input string.
     * @return The LocalDate object.
     */
    public static LocalDate parseDateInput(String input) {
        Date date = extractDate(input);
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Parses a time input string into a LocalTime object.
     *
     * @param input The time input string.
     * @return The LocalTime object.
     */
    public static LocalTime parseTimeInput(String input) {
        Date date = extractDate(input);
        return LocalTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Formats a LocalDateTime object into a human-readable string.
     *
     * @param dateTime The LocalDateTime object.
     * @return The formatted string.
     */
    public static String formatLocalDateTime(LocalDateTime dateTime) {
        PrettyTime prettyTime = new PrettyTime();
        return prettyTime.format(Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()));
    }

    /**
     * Formats a LocalDate object into a human-readable string.
     *
     * @param date The LocalDate object.
     * @return The formatted string.
     */
    public static String formatLocalDate(LocalDate date) {
        PrettyTime prettyTime = new PrettyTime();
        return prettyTime.format(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    /**
     * Formats a LocalTime object into a human-readable string.
     *
     * @param time The LocalTime object.
     * @return The formatted string.
     */
    public static String formatLocalTime(LocalTime time) {
        PrettyTime prettyTime = new PrettyTime();
        return prettyTime.format(Date.from(time.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant()));
    }

    /**
     * Extracts a date from the input string using the Natty library.
     *
     * @param input The input string.
     * @return The extracted date.
     */
    private static Date extractDate(String input) {
        Parser parser = new Parser();
        List<DateGroup> groups = parser.parse(input);

        if (groups.isEmpty()) {
            throw new IllegalArgumentException(INVALID_DATE_FORMAT);
        }

        DateGroup group = groups.get(0);
        String matchedText = group.getText().toLowerCase();

        if (!input.toLowerCase().equals(matchedText)) {
            throw new IllegalArgumentException(String.format(PARTIAL_DATE_FORMAT, matchedText));
        }
        return group.getDates().get(0);
    }
}
