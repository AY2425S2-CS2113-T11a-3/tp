package seedu.internsprint.handler;

import seedu.internsprint.command.AddGeneralCommand;
import seedu.internsprint.command.AddHardwareCommand;
import seedu.internsprint.command.AddSoftwareCommand;
import seedu.internsprint.command.ByeCommand;
import seedu.internsprint.command.Command;
import seedu.internsprint.command.EditCommand;
import seedu.internsprint.command.DeleteCommand;
import seedu.internsprint.internship.Internship;
import seedu.internsprint.internship.InternshipList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static seedu.internsprint.util.InternSprintExceptionMessages.ILLEGAL_VALUE_INPUT;
import static seedu.internsprint.util.InternSprintExceptionMessages.INVALID_COMMAND_TYPE;
import static seedu.internsprint.util.InternSprintExceptionMessages.INVALID_INDEX;
import static seedu.internsprint.util.InternSprintExceptionMessages.INVALID_INDEX_RANGE;
import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_INDEX;
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
        case "delete":
            command = new DeleteCommand();
            break;
        default:
            throw new IllegalArgumentException(String.format(INVALID_COMMAND_TYPE, commandType));
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

    public static String[] validateIndex(String index, InternshipList internships) {
        if (index.isEmpty()) {
            throw new IllegalArgumentException(MISSING_INDEX);
        }
        try {
            HashMap<String, ArrayList<Internship>> internshipsMap = internships.getInternshipMap();
            int taskCount = internships.getInternshipCount();

            int indexValue = Integer.parseInt(index);
            if (indexValue < 1 || indexValue > taskCount) {
                throw new IllegalArgumentException(INVALID_INDEX_RANGE);
            }
            String type;
            if (indexValue <= internshipsMap.get("general").size()) {
                type = "general";
            } else if (indexValue <= internshipsMap.get("general").size() + internshipsMap.get("software").size()) {
                type = "software";
            } else {
                type = "hardware";
            }
            return new String[]{type, Integer.toString(indexValue - 1)};
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_INDEX);
        }
    }

}
