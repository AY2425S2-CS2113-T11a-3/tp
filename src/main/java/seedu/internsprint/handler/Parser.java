package seedu.internsprint.handler;

import seedu.internsprint.command.AddGeneralCommand;
import seedu.internsprint.command.AddHardwareCommand;
import seedu.internsprint.command.AddSoftwareCommand;
import seedu.internsprint.command.ByeCommand;
import seedu.internsprint.command.Command;
import seedu.internsprint.command.DescriptionCommand;
import seedu.internsprint.command.EditCommand;
import seedu.internsprint.command.DeleteCommand;
import seedu.internsprint.command.ListCommand;

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
        case "list":
            command = new ListCommand();
            break;
        case "desc":
            command = new DescriptionCommand();
            break;
        case "delete":
            command = parseDeleteCommand(params);
            break;
        default:
            throw new IllegalArgumentException("Unknown command type: " + commandType);
        }
        parseKeyValuePairs(params, command);
        return command;
    }

    private static Command parseDeleteCommand(String params) {
        String[] paramParts = params.split(" ");

        if (paramParts.length == 1) {
            return new DeleteCommand(paramParts[0], -1);
        }

        if (paramParts.length != 2) {
            return new DeleteCommand("", -1);
        }

        String category = paramParts[0];
        int index;

        if (!DeleteCommand.VALID_CATEGORIES.contains(category.toLowerCase())) {
            return new DeleteCommand("", -1);
        }

        try {
            index = Integer.parseInt(paramParts[1]);
        } catch (NumberFormatException e) {
            return new DeleteCommand("", -1);
        }

        return new DeleteCommand(category, index);
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
