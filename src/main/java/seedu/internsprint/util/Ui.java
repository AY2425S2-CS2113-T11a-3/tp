package seedu.internsprint.util;

import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.internship.Internship;

import java.util.List;
import java.util.Scanner;

import static seedu.internsprint.util.InternSprintMessages.LOGO;
import static seedu.internsprint.util.InternSprintMessages.WELCOME_MESSAGE;

public class Ui {
    private static final int DASH_LINE_WIDTH = 120;
    private static final String DIVIDER = "-".repeat(DASH_LINE_WIDTH);
    private static final Scanner scanner = new Scanner(System.in);

    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void showWelcomeMessage() {
        System.out.println(DIVIDER);
        System.out.println(LOGO);
        System.out.println(WELCOME_MESSAGE);
        System.out.println(DIVIDER);
    }

    public static void showError(String message) {
        System.out.println(DIVIDER);
        System.out.println("    " + ERROR_PREFIX + message);
        System.out.println(DIVIDER);
    }

    public static void showError(List<String> messages) {
        System.out.println("    " + ERROR_PREFIX + messages.get(0));
        for (int i = 1; i < messages.size(); i++) {
            System.out.println("    " + messages.get(i));
        }
    }

    public static void showResultToUser(CommandResult result) {
        if (result.isSuccessful()) {
            System.out.println(DIVIDER);
            for (String feedback : result.getFeedbackToUser()) {
                System.out.println("    " + feedback);
            }
            if (result.getRelevantInternships() != null) {
                showRelevantInternshipsToUser(result.getRelevantInternships());
            }
            System.out.println(DIVIDER);
        } else {
            System.out.println(DIVIDER);
            showError(result.getFeedbackToUser());
            System.out.println(DIVIDER);
        }
    }

    public static void showRelevantInternshipsToUser(List<Internship> relevantInternships) {
        System.out.println("    Here is the list of relevant internships:");
        for (int i = 0; i < relevantInternships.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + relevantInternships.get(i));
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
