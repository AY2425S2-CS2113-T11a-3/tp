package seedu.internsprint.logic.parser;

import seedu.internsprint.logic.command.internship.AddGeneralInternshipCommand;
import seedu.internsprint.logic.command.internship.AddHardwareInternshipCommand;
import seedu.internsprint.logic.command.internship.AddInterviewCommand;
import seedu.internsprint.logic.command.internship.AddSoftwareInternshipCommand;
import seedu.internsprint.logic.command.ByeCommand;
import seedu.internsprint.logic.command.internship.EditCommand;
import seedu.internsprint.logic.command.internship.SortInterviewCommand;
import seedu.internsprint.logic.command.user.UserProfileCommand;
import seedu.internsprint.logic.command.internship.DeleteCommand;
import seedu.internsprint.logic.command.internship.DescriptionCommand;
import seedu.internsprint.logic.command.user.ViewUserCommand;
import seedu.internsprint.logic.command.internship.ListCommand;
import seedu.internsprint.logic.command.internship.HelpCommand;
import seedu.internsprint.logic.command.Command;
import seedu.internsprint.model.internship.Internship;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.logic.command.internship.FindCommand;
import seedu.internsprint.logic.command.user.ProjectGeneralCommand;
import seedu.internsprint.logic.command.user.ProjectHardwareCommand;
import seedu.internsprint.logic.command.user.ProjectSoftwareCommand;
import seedu.internsprint.logic.command.user.ViewHardwareProjectsCommand;
import seedu.internsprint.logic.command.user.ViewSoftwareProjectsCommand;
import seedu.internsprint.logic.command.user.ViewGeneralProjectsCommand;

import seedu.internsprint.util.InternSprintLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static seedu.internsprint.util.InternSprintExceptionMessages.ILLEGAL_VALUE_INPUT;
import static seedu.internsprint.util.InternSprintExceptionMessages.INVALID_COMMAND_TYPE;
import static seedu.internsprint.util.InternSprintExceptionMessages.INVALID_INDEX;
import static seedu.internsprint.util.InternSprintExceptionMessages.INVALID_INDEX_RANGE;
import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_INDEX;
import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_VALUE_INPUT;
import static seedu.internsprint.util.InternSprintExceptionMessages.REPEATED_FLAG;

/**
 * Parses user input.
 */
public class CommandParser {
    private static final Logger logger = InternSprintLogger.getLogger();

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param userInput User input string.
     * @return Command object corresponding to the user input.
     */
    public static Command parseCommand(String userInput) {
        logger.log(Level.INFO, String.format("User command inside parseCommand: %s", userInput));
        assert userInput != null : "User input should not be null";

        String[] commandTypeAndParams = splitCommandTypeAndParams(userInput.trim());
        assert commandTypeAndParams.length > 0 : "Command type should not be empty";

        String commandType = commandTypeAndParams[0];
        String params = commandTypeAndParams.length > 1 ? commandTypeAndParams[1] : "";

        Command command;
        switch (commandType) {
        case "add software":
            command = new AddSoftwareInternshipCommand();
            break;
        case "add hardware":
            command = new AddHardwareInternshipCommand();
            break;
        case "add general":
            command = new AddGeneralInternshipCommand();
            break;
        case "interviewfor":
            command = new AddInterviewCommand();
            break;
        case "sortInterviews":
            command = new SortInterviewCommand();
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
        case "find":
            command = new FindCommand();
            break;
        case "desc":
            command = new DescriptionCommand();
            break;
        case "help":
            command = new HelpCommand();
            break;
        case "delete":
            command = new DeleteCommand();
            break;
        case "my":
            command = new UserProfileCommand();
            break;
        case "view user":
            command = new ViewUserCommand();
            break;
        case "project general":
            command = new ProjectGeneralCommand();
            break;
        case "view general":
            command = new ViewGeneralProjectsCommand();
            break;
        case "view software":
            command = new ViewSoftwareProjectsCommand();
            break;
        case "view hardware":
            command = new ViewHardwareProjectsCommand();
            break;
        case "project software":
            command = new ProjectSoftwareCommand();
            break;
        case "project hardware":
            command = new ProjectHardwareCommand();
            break;
        default:
            throw new IllegalArgumentException(String.format(INVALID_COMMAND_TYPE, commandType));
        }
        parseKeyValuePairs(params, command);
        return command;
    }

    /**
     * Splits the user input into the command type and the parameters.
     *
     * @param userInput User input string.
     * @return Array containing the command type and the parameters.
     */
    protected static String[] splitCommandTypeAndParams(String userInput) {
        String[] flagCommands = {"add software", "add hardware", "add general", "project general",
            "project software", "project hardware", "view software", "view hardware", "view general",
            "view user"};
        for (String command : flagCommands) {
            if (userInput.toLowerCase().startsWith(command)) {
                return new String[]{command, userInput.substring(command.length()).trim()};
            }
        }
        String[] commandTypeAndParams = userInput.split(" ", 2);
        String commandType = commandTypeAndParams[0];
        String params = commandTypeAndParams.length > 1 ? commandTypeAndParams[1] : "";

        return new String[]{commandType.toLowerCase(), params};
    }

    /**
     * Parses the key-value pairs in the parameters string and sets them in the Command object.
     *
     * @param params  Parameters string.
     * @param command Command object.
     */
    protected static void parseKeyValuePairs(String params, Command command) {
        HashMap<String, String> keyValueMap = new HashMap<>();

        if (params.isEmpty()) {
            return;
        }

        String[] parts = params.trim().split("(?=\\s*/[a-zA-Z]+)");
        if (!parts[0].trim().startsWith("/")) {
            logger.info("Found a parameter that is not part of a flag.");
            keyValueMap.put("description", parts[0].trim());
            if (parts.length == 1) {
                command.setParameters(keyValueMap);
                return;
            }
            parts = Arrays.copyOfRange(parts, 1, parts.length);
        }

        for (String part : parts) {
            if (part.trim().isEmpty()) {
                continue;
            }
            String[] keyValue = part.trim().split("\\s+", 2);
            if (keyValue.length < 2) {
                logger.warning("Key found with no value.");
                throw new IllegalArgumentException(String.format(MISSING_VALUE_INPUT, keyValue[0]));
            }

            String key = keyValue[0].trim();
            String value = keyValue[1].trim();

            if (keyValueMap.containsKey(key)) {
                logger.warning("Repeated flag found.");
                throw new IllegalArgumentException(String.format(REPEATED_FLAG, key));
            }

            if (value.contains("/")) {
                logger.warning("'/' found in value.");
                throw new IllegalArgumentException(ILLEGAL_VALUE_INPUT);
            }

            keyValueMap.put(key, value);
        }

        command.setParameters(keyValueMap);
    }

    /**
     * Splits the input string from the user into individual words for user profile class
     *
     * @param input string in key-value pair to be split into words
     * @return Array list of individual words in a string
     */
    public static ArrayList<String> splitToWords(String input) {
        return Arrays.stream(input.split("\\s*,\\s*"))
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Validates the index input by the user.
     *
     * @param index       Index input by the user.
     * @param internships InternshipList object.
     * @return Array containing the type and index of the internship.
     */
    public static String[] validateIndex(String index, InternshipList internships) {
        if (index.isEmpty()) {
            logger.warning("Index is empty.");
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
            if (indexValue <= internshipsMap.get("software").size()) {
                type = "software";
            } else if (indexValue <= internshipsMap.get("software").size() + internshipsMap.get("hardware").size()) {
                indexValue -= internshipsMap.get("software").size();
                type = "hardware";
            } else {
                indexValue -= internshipsMap.get("software").size();
                indexValue -= internshipsMap.get("hardware").size();
                type = "general";
            }
            return new String[]{type, Integer.toString(indexValue - 1)};
        } catch (NumberFormatException e) {
            logger.warning("Index is not a number.");
            throw new IllegalArgumentException(INVALID_INDEX);
        }
    }
}
