package seedu.internsprint.command;

import seedu.internsprint.internship.Internship;
import seedu.internsprint.internship.InternshipList;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static seedu.internsprint.util.InternSprintMessages.ADD_MESSAGE_SUCCESS;
import static seedu.internsprint.util.InternSprintMessages.MESSAGE_DUPLICATE_INTERNSHIP;
import static seedu.internsprint.util.InternSprintMessages.LIST_COUNT_MESSAGE;

public abstract class AddCommand extends Command {
    protected final Set<String> requiredParameters;

    public AddCommand(Set<String> requiredParameters) {
        this.requiredParameters = requiredParameters;
    }

    protected boolean isValidParameters() {
        return parameters.keySet().containsAll(requiredParameters);
    }

    protected abstract String getUsageMessage();

    protected abstract Internship createInternship();

    @Override
    public CommandResult execute(InternshipList internships) {
        CommandResult result;
        if (!isValidParameters()) {
            result = new CommandResult(getUsageMessage());
            result.setSuccessful(false);
            return result;
        }

        Internship toAdd = createInternship();

        if (internships.contains(toAdd)) {
            result = new CommandResult(MESSAGE_DUPLICATE_INTERNSHIP);
            result.setSuccessful(false);
            return result;
        }

        List<String> feedback = new ArrayList<>();

        internships.addInternship(toAdd);
        feedback.add(ADD_MESSAGE_SUCCESS);
        feedback.add(toAdd.toString());
        feedback.add(String.format(LIST_COUNT_MESSAGE, internships.getInternshipCount()));
        result = new CommandResult(feedback);
        result.setSuccessful(true);
        return result;
    }
}
