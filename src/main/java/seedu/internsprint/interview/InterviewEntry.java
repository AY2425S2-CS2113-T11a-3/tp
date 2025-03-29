package seedu.internsprint.interview;

import seedu.internsprint.internship.Internship;

public class InterviewEntry {
    private final Internship internship;
    private final Interview interview;

    public InterviewEntry(Internship internship, Interview interview) {
        this.internship = internship;
        this.interview = interview;
    }

    public Internship getInternship() {
        return internship;
    }

    public Interview getInterview() {
        return interview;
    }
}
