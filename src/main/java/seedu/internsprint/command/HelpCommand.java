package seedu.internsprint.command;

import seedu.internsprint.internship.InternshipList;
import seedu.internsprint.util.InternSprintExceptionMessages;

import java.util.LinkedHashMap;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " : Shows information on how to use a specific command " +
            "or a list of all commands.\n"
            + "Parameters: [COMMAND] (optional)\n"
            + "Example: " + COMMAND_WORD + " add general";

    private static final LinkedHashMap<String, String> COMMAND_HELP_MESSAGES = new LinkedHashMap<>();

    static {
        COMMAND_HELP_MESSAGES.put("add software", "    The add software function allows users to add " +
                "a software internship to the internship list.\n" +
                "    Format: add software /c COMPANY_NAME /r ROLE /tech TECHNOLOGIES");
        COMMAND_HELP_MESSAGES.put("add hardware", "    The add hardware function allows users to add " +
                "a hardware internship to the internship list.\n" +
                "    Format:  add hardware /c COMPANY_NAME /r ROLE /tech TECHNOLOGIES");
        COMMAND_HELP_MESSAGES.put("delete", "    The delete function allows users to delete " +
                "an internship from the internship list. \n" +
                "    Format: delete CATEGORY INDEX");
        COMMAND_HELP_MESSAGES.put("bye", "    Exits the program.\n" +
                "    Format: bye");
    }

    @Override
    protected boolean isValidParameters() {
        return parameters.isEmpty() || parameters.size() == 1;
    }

    @Override
    public CommandResult execute(InternshipList internships) {
        String feedback;
        boolean isSuccess = false;

        if (!isValidParameters()) {
            feedback = InternSprintExceptionMessages.HELP_INVALID_PARAMETERS;
        } else if (parameters.isEmpty()) {
            StringBuilder helpMessage = new StringBuilder("Here is the guide for all available commands:\n\n");
            for (String command : COMMAND_HELP_MESSAGES.keySet()) {
                helpMessage.append(command).append("\n");
                helpMessage.append(COMMAND_HELP_MESSAGES.get(command)).append("\n\n");
            }
            feedback = helpMessage.toString();
            isSuccess = true;
        } else {
            String commandName = parameters.values().iterator().next();
            if (COMMAND_HELP_MESSAGES.containsKey(commandName)) {
                feedback = "help " + commandName + "\n" + COMMAND_HELP_MESSAGES.get(commandName);
                isSuccess = true;
            } else {
                feedback = InternSprintExceptionMessages.HELP_INVALID_PARAMETERS;
            }
        }

        CommandResult result = new CommandResult(feedback);
        result.setSuccessful(isSuccess);

        return result;
    }
}
