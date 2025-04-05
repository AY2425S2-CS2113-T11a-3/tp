package seedu.internsprint.logic.command.internship;

import seedu.internsprint.logic.command.Command;
import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.internship.Internship;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.internship.interview.Interview;
import seedu.internsprint.model.internship.interview.InterviewEntry;
import seedu.internsprint.model.userprofile.UserProfile;
import seedu.internsprint.util.InternSprintLogger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.internsprint.util.InternSprintExceptionMessages.SORT_INTERVIEWS_INVALID_PARAMS;
import static seedu.internsprint.util.InternSprintMessages.NO_INTERVIEWS_SCHEDULED;
import static seedu.internsprint.util.InternSprintMessages.SORT_INTERVIEWS_MESSAGE_SUCCESS;

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
        logger.log(Level.INFO, "Executing sortInterviews command");
        assert internships != null : "Internship list cannot be null";
        assert user != null : "UserProfile cannot be null";

        List<String> feedback = new ArrayList<>();
        List<InterviewEntry> allEntries = collectAllInterviews(internships);
        CommandResult result;

        if (!isValidParameters()) {
            logger.log(Level.WARNING, "There are invalid parameters so error result is output to user.");
            feedback.add(SORT_INTERVIEWS_INVALID_PARAMS);
            feedback.add(MESSAGE_USAGE);
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            return result;
        }

        allEntries.sort(Comparator.comparing(entry -> LocalDateTime.of(
                        entry.getInterview().getUnformattedInterviewDate(),
                        entry.getInterview().getUnformattedInterviewStartTime()
                )
        ));
        logger.log(Level.INFO, "Interview entries sorted successfully.");

        if (allEntries.isEmpty()) {
            feedback.add(NO_INTERVIEWS_SCHEDULED);
        } else {
            feedback.add(SORT_INTERVIEWS_MESSAGE_SUCCESS);
            for (int i = 0; i < allEntries.size(); i++) {
                InterviewEntry entry = allEntries.get(i);
                Interview interview = entry.getInterview();
                Internship internship = entry.getInternship();

                assert internship != null : "Internship cannot be null";
                assert interview != null : "Interview cannot be null";

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

        logger.log(Level.INFO, "sortInterviews command executed successfully");
        result = new CommandResult(feedback);
        result.setSuccessful(true);
        return result;
    }

    private List<InterviewEntry> collectAllInterviews(InternshipList internshipList) {
        logger.log(Level.INFO, "Collecting all interviews from internship list");
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
        logger.log(Level.INFO, "Successfully collected a total of" + allInterviews.size() + " interview entries.");
        return allInterviews;
    }

    private void collectNextRounds(Internship internship, List<Interview> rounds, List<InterviewEntry> result) {
        logger.log(Level.FINE, "Entering collectNextRounds with " + rounds.size() + "rounds");
        for (Interview round : rounds) {
            assert round != null : "Interview round cannot be null";
            result.add(new InterviewEntry(internship, round));
            collectNextRounds(internship, round.getNextRounds(), result);
        }
        logger.log(Level.FINE, "Exiting collectNextRounds");
    }

}
