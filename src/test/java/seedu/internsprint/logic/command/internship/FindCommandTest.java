package seedu.internsprint.logic.command.internship;

import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.internship.HardwareInternship;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.internship.SoftwareInternship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internsprint.model.userprofile.UserProfile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internsprint.util.InternSprintMessages.NO_INTERNSHIPS_FOUND;
import static seedu.internsprint.util.InternSprintMessages.NUMBER_OF_INTERNSHIPS_FOUND;

class FindCommandTest {

    private InternshipList internshipList;

    @BeforeEach
    void setUp() {
        SoftwareInternship internship1 = new SoftwareInternship("Facebook", "software Intern", "C++");
        HardwareInternship internship2 = new HardwareInternship("Google", "hardware Intern", "Java");
        internshipList = new InternshipList();
        internshipList.addInternship(internship1);
        internshipList.addInternship(internship2);
    }

    @Test
    void isValidParameters_provideOneParameter_returnsTrue() {
        FindCommand findCommand = new FindCommand();
        findCommand.getParameters().put("description", "software");
        assertTrue(findCommand.isValidParameters());
    }

    @Test
    void isValidParameters_provideNoParameters_returnsFalse() {
        FindCommand findCommand = new FindCommand();
        assertFalse(findCommand.isValidParameters());
    }

    @Test
    void execute_provideOnlyDescription_executesSuccessfully() {
        FindCommand findCommand = new FindCommand();
        findCommand.getParameters().put("description", "software");
        CommandResult result = findCommand.execute(internshipList, new UserProfile());
        String feedback = String.format(NUMBER_OF_INTERNSHIPS_FOUND, 1);
        assertTrue(result.isSuccessful());
        assertEquals(feedback, result.getFeedbackToUser().get(0));
    }

    @Test
    void execute_provideOnlyCompanyName_returnsTrue() {
        FindCommand findCommand = new FindCommand();
        findCommand.getParameters().put("/c", "Facebook");
        CommandResult result = findCommand.execute(internshipList, new UserProfile());
        assertTrue(result.isSuccessful());
    }

    @Test
    void execute_provideOnlyRole_returnsTrue() {
        FindCommand findCommand = new FindCommand();
        findCommand.getParameters().put("/r", "software Intern");
        CommandResult result = findCommand.execute(internshipList,  new UserProfile());
        assertTrue(result.isSuccessful());
    }

    @Test
    void execute_provideKeyWithNoRelevantInternships_returnsEmptyList() {
        FindCommand findCommand = new FindCommand();
        findCommand.getParameters().put("description", "general");
        CommandResult result = findCommand.execute(internshipList,  new UserProfile());
        assertTrue(result.isSuccessful());
        assertEquals(NO_INTERNSHIPS_FOUND, result.getFeedbackToUser().get(0));
    }

    @Test
    void execute_provideNoValidInput_returnsFalse() {
        FindCommand findCommand = new FindCommand();
        CommandResult result = findCommand.execute(internshipList,  new UserProfile());
        assertFalse(result.isSuccessful());
    }

    @Test
    void execute_provideEmptyInputs_returnsFalse() {
        FindCommand findCommand = new FindCommand();
        findCommand.getParameters().put("description", " ");
        findCommand.getParameters().put("/c", " ");
        findCommand.getParameters().put("/r", " ");
        CommandResult result = findCommand.execute(internshipList,  new UserProfile());
        assertFalse(result.isSuccessful());
    }
}
