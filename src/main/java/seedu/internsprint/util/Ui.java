package seedu.internsprint.util;

import seedu.internsprint.command.CommandResult;

import java.util.Scanner;

import static seedu.internsprint.util.InternSprintMessages.BYE_MESSAGE;
import static seedu.internsprint.util.InternSprintMessages.WELCOME_MESSAGE;

public class Ui {
    private static final String DIVIDER = "===================================================";
    private static final Scanner scanner = new Scanner(System.in);

    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void showDivider() {
        System.out.println(DIVIDER);
    }

    public static void showWelcomeMessage() {
        System.out.println(DIVIDER);
        System.out.println(WELCOME_MESSAGE);
        System.out.println(DIVIDER);
        System.out.println();
    }

    public static void showExitMessage() {
        System.out.println(BYE_MESSAGE);
    }

    public static void showError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public static void showResultToUser(CommandResult result) {
        if (result.isSuccessful()) {
            for (String feedback : result.getFeedbackToUser()) {
                System.out.println(feedback);
            }
        } else {
            showError(result.getFeedbackToUser().get(0));
        }
    }

    public static String getUserCommand() {
        System.out.print("> ");
        String input = scanner.nextLine();
        while (input.trim().isEmpty()) {
            input = scanner.nextLine();
        }
        return input;
    }
}
