package seedu.internsprint.handler;

import seedu.internsprint.command.AddGeneralCommand;
import seedu.internsprint.command.AddHardwareCommand;
import seedu.internsprint.command.AddSoftwareCommand;
import seedu.internsprint.command.ByeCommand;
import seedu.internsprint.command.Command;
import seedu.internsprint.command.EditCommand;
import seedu.internsprint.command.HelpCommand;

import java.util.Arrays;
import java.util.HashMap;

import static seedu.internsprint.util.InternSprintExceptionMessages.ILLEGAL_VALUE_INPUT;
import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_VALUE_INPUT;

public class Parser {
    public static Command parseCommand(String userInput) {
        String[] commandTypeAndParams = splitCommandTypeAndParams(userInput.trim());
        String commandType = commandTypeAndParams[0];
        String params = commandTypeAndParams.length > 1 ? commandTypeAndParams[1] : "";

        Command command;
        switch (commandType) {
        case "add software":
            command = new AddSoftwareCommand();
            break;
        case "add hardware":
            command = new AddHardwareCommand();
            break;
        case "add general":
            command = new AddGeneralCommand();
            break;
        case "bye":
            command = new ByeCommand();
            break;
        case "edit":
            command = new EditCommand();
            break;
        case "help":
            command = new HelpCommand();
            break;
            /*
        case "delete":
            String[] deleteArgs = params.split(" ", 2);
            if (deleteArgs.length < 2) {
                throw new IllegalArgumentException("Please specify both the
                category (software/hardware/general) and the index.");
            }

            String category = deleteArgs[0].toLowerCase();
            int index;
            try {
                index = Integer.parseInt(deleteArgs[1].trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid index. Please enter a valid number for the index.");
            }
            command = new DeleteCommand(category, index);
            break;*/
        default:
            throw new IllegalArgumentException("Unknown command type: " + commandType);
        }
        parseKeyValuePairs(params, command);
        return command;
    }

    private static String[] splitCommandTypeAndParams(String userInput) {
        String[] multiWordCommands = {"add software", "add hardware", "add general", "edit"};
        for (String command : multiWordCommands) {
            if (userInput.startsWith(command)) {
                return new String[]{command, userInput.substring(command.length()).trim()};
            }
        }
        String[] commandTypeAndParams = userInput.split(" ", 2);
        String commandType = commandTypeAndParams[0];
        String params = commandTypeAndParams.length > 1 ? commandTypeAndParams[1] : "";

        return new String[]{commandType, params};
    }

    protected static void parseKeyValuePairs(String params, Command command) {
        HashMap<String, String> keyValueMap = new HashMap<>();

        if (params.isEmpty()) {
            return;
        }

        String[] parts = params.trim().split("(?=\\s*/[a-zA-Z]+)");
        if (!parts[0].trim().startsWith("/")) {
            keyValueMap.put("description", parts[0].trim());
            if (parts.length == 1) {
                command.setParameters(keyValueMap);
                return;
            }
            parts = Arrays.copyOfRange(parts, 1, parts.length);
        }

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].trim().isEmpty()) {
                continue;
            }
            String[] keyValue = parts[i].trim().split("\\s+", 2);
            if (keyValue.length < 2) {
                throw new IllegalArgumentException(String.format(MISSING_VALUE_INPUT, keyValue[0]));
            }

            String key = keyValue[0].trim();
            String value = keyValue[1].trim();

            if (value.contains("/")) {
                throw new IllegalArgumentException(ILLEGAL_VALUE_INPUT);
            }
            keyValueMap.put(key, value);
        }

        command.setParameters(keyValueMap);
    }
}
