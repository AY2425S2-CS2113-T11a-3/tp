package seedu.internsprint.interview;

import seedu.internsprint.handler.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_REQUIRED_PARAMETERS;

public class Interview {

    /* Mandatory required parameters to create an Interview */
    protected LocalDate interviewDate;
    protected LocalTime interviewStartTime;
    protected LocalTime interviewEndTime;
    protected String interviewType;

    protected ArrayList<Interview> nextRounds = new ArrayList<Interview>();

    /*Optional Parameters to create an Interview */
    protected String interviewerEmail;
    protected String notes;

    protected int roundCounter = 0;


    public Interview(String interviewDate, String interviewStartTime, String interviewEndTime,
                     String interviewType) {
        if (interviewDate == null || interviewStartTime == null || interviewEndTime == null || interviewType == null) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS,
                "/date, /start, /end or /type"));
        }

        if (interviewDate.isBlank() || interviewStartTime.isBlank() || interviewEndTime.isBlank() ||
            interviewType.isBlank()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS,
                "/date, /start, /end or /type"));
        }

        this.interviewDate = DateTimeParser.parseDateInput(interviewDate);
        this.interviewStartTime = DateTimeParser.parseTimeInput(interviewStartTime);
        this.interviewEndTime = DateTimeParser.parseTimeInput(interviewEndTime);
        this.interviewType = interviewType;

        setRoundCounter(this.roundCounter);
    }

    public Interview(String interviewDate, String interviewStartTime, String interviewEndTime, String interviewType,
                     String interviewerEmail, String notes) {
        if (interviewDate == null || interviewStartTime == null || interviewEndTime == null || interviewType == null) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS,
                "/date, /start, /end or /type"));
        }

        if (interviewDate.isBlank() || interviewStartTime.isBlank() || interviewEndTime.isBlank() ||
            interviewType.isBlank()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS,
                "/date, /start, /end or /type"));
        }

        this.interviewDate = DateTimeParser.parseDateInput(interviewDate);
        this.interviewStartTime = DateTimeParser.parseTimeInput(interviewStartTime);
        this.interviewEndTime = DateTimeParser.parseTimeInput(interviewEndTime);
        this.interviewType = interviewType;

        if (interviewerEmail != null && !interviewerEmail.isBlank()) {
            this.interviewerEmail = interviewerEmail;
        }

        if (notes != null && !notes.isBlank()) {
            this.notes = notes;
        }

        this.roundCounter = 0;
    }

    @Override
    public String toString() {
        String interviewString = "    Interview Date: " + interviewDate +
            ", Start Time: " + interviewStartTime +
            ", End Time: " + interviewEndTime +
            ", Round Name: " + interviewType;
        if (interviewerEmail != null) {
            interviewString += ", Interviewer Email: " + interviewerEmail;
        }
        if (notes != null) {
            interviewString += ", Notes: " + notes;
        }
        return interviewString;
    }

    public ArrayList<String> toDescription() {
        ArrayList<String> interviewString = new ArrayList<>();
        if (roundCounter == 0) {
            interviewString.add("Interview Details:");
        } else {
            interviewString.add("Round 1 Interview Details:");
        }
        interviewString.add("    Date: " + interviewDate);
        interviewString.add("    Start Time: " + interviewStartTime);
        interviewString.add("    End Time: " + interviewEndTime);
        interviewString.add("    Interview Type: " + interviewType);
        if (interviewerEmail != null) {
            interviewString.add("    Interviewer Email: " + interviewerEmail);
        }
        if (notes != null) {
            interviewString.add("    Notes: " + notes);
        }

        if (roundCounter != 0) {
            for (int i = 0; i < nextRounds.size(); i++) {
                interviewString.add("Round " + (i + 2) + " Interview Details:");
                interviewString.add("    " + nextRounds.get(i).toDescription());
            }
        }

        return interviewString;
    }

    public void addInterviewRound(Interview round) {
        if (round != null) {
            this.nextRounds.add(round);
            roundCounter++;
        }
    }

    public String getInterviewDate() {
        return DateTimeParser.formatLocalDate(interviewDate);
    }

    public void setInterviewDate(LocalDate interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getInterviewStartTime() {
        return DateTimeParser.formatLocalTime(interviewStartTime);
    }

    public void setInterviewStartTime(LocalTime interviewStartTime) {
        this.interviewStartTime = interviewStartTime;
    }

    public String getInterviewEndTime() {
        return DateTimeParser.formatLocalTime(interviewEndTime);
    }

    public void setInterviewEndTime(LocalTime interviewEndTime) {
        this.interviewEndTime = interviewEndTime;
    }

    public String getInterviewerEmail() {
        return interviewerEmail;
    }

    public void setInterviewerEmail(String interviewerEmail) {
        this.interviewerEmail = interviewerEmail;
    }

    public String getInterviewType() {
        return interviewType;
    }

    public void setInterviewType(String interviewType) {
        this.interviewType = interviewType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public void setRoundCounter(int roundCounter) {
        this.roundCounter += roundCounter;
    }

    public ArrayList<Interview> getNextRounds() {
        return nextRounds;
    }

    public LocalDate getInterviewDateAsLocalDate() {
        return interviewDate;
    }

    public LocalTime getInterviewStartTimeAsLocalTime() {
        return interviewStartTime;
    }

    public LocalTime getInterviewEndTimeAsLocalTime() {
        return interviewEndTime;
    }
}
