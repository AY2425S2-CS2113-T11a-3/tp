package seedu.internsprint.command;

import seedu.internsprint.internship.Internship;
import seedu.internsprint.internship.InternshipList;
import seedu.internsprint.interview.Interview;
import seedu.internsprint.interview.InterviewEntry;
import seedu.internsprint.userprofile.UserProfile;
import seedu.internsprint.util.InternSprintLogger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class SortInterviewCommand extends Command {
    public static final String COMMAND_WORD = "sortInterviews";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the internship by the name of the interview " +
            "entry.\n "
            + " Parameters: None\n"
            + "Example: " + COMMAND_WORD;
    private static final Logger logger = InternSprintLogger.getLogger();

    @Override
    public String getCommandType() {
        return "interview";
    }

    @Override
    protected boolean isValidParameters() {
        return parameters.isEmpty();
    }

    @Override
    public CommandResult execute(InternshipList internships, UserProfile user) {
        List<String> feedback = new ArrayList<>();
        List<InterviewEntry> allEntries = collectAllInterviews(internships);

        allEntries.sort(Comparator.comparing(entry -> LocalDateTime.of(
                        entry.getInterview().getUnformattedInterviewDate(),
                        entry.getInterview().getUnformattedInterviewStartTime()
                )
        ));

        if (allEntries.isEmpty()) {
            feedback.add("You have no interviews scheduled");
        } else {
            feedback.add("Here are yout interviews sorted by date and time:");
            for (int i = 0; i < allEntries.size(); i++) {
                InterviewEntry entry = allEntries.get(i);
                Interview interview = entry.getInterview();
                Internship internship = entry.getInternship();

                feedback.add((i + 1) + ". " + internship.getCompanyName() + " - " + internship.getRole());
                feedback.add("    Date: " + interview.getUnformattedInterviewDate());
                feedback.add("    Start: " + interview.getUnformattedInterviewStartTime());
                feedback.add("    End: " + interview.getUnformattedInterviewEndTime());
                feedback.add("    Type: " + interview.getInterviewType());

                if (interview.getInterviewerEmail() != null) {
                    feedback.add("    Email: " + interview.getInterviewerEmail());
                }

                if (interview.getNotes() != null) {
                    feedback.add("    Notes: " + interview.getNotes());
                }
                feedback.add("");
            }
        }

        CommandResult result = new CommandResult(feedback);
        result.setSuccessful(true);
        return result;
    }

    private List<InterviewEntry> collectAllInterviews(InternshipList internshipList) {
        List<InterviewEntry> allInterviews = new ArrayList<>();

        for (ArrayList<Internship> internships : internshipList.getInternshipMap().values()) {
            for (Internship internship : internships) {
                Interview interview = internship.getInterview();
                if (interview != null) {
                    allInterviews.add(new InterviewEntry(internship, interview));
                    collectNextRounds(internship, interview.getNextRounds(), allInterviews);
                }
            }
        }
        return allInterviews;
    }

    private void collectNextRounds(Internship internship, List<Interview> rounds, List<InterviewEntry> result) {
        for (Interview round : rounds) {
            result.add(new InterviewEntry(internship, round));
            collectNextRounds(internship, round.getNextRounds(), result);
        }
    }

}
