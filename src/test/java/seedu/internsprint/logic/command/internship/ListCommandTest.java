package seedu.internsprint.logic.command.internship;

import org.junit.jupiter.api.Test;

import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.internship.HardwareInternship;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.internship.SoftwareInternship;
import seedu.internsprint.model.userprofile.UserProfile;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internsprint.util.InternSprintMessages.LIST_MESSAGE_SUCCESS;

public class ListCommandTest {
    private static SoftwareInternship internship1 = new SoftwareInternship("Facebook", "software Intern", "C++");
    private static SoftwareInternship internship2 = new SoftwareInternship("Google", "SWE Intern", "Java");
    private static HardwareInternship internship3 = new HardwareInternship("Accenture", "hardware Intern", "baremetal");
    private static UserProfile userProfile = new UserProfile();

    @Test
    void isValidParameters_provideNoParameters_returnsTrue() {
        ListCommand listcommand = new ListCommand();
        assertTrue(listcommand.isValidParameters());
    }

    @Test
    void isValidParameters_provideExtraParameters_returnsFalse() {
        ListCommand listcommand = new ListCommand();
        HashMap<String, String> parameters = listcommand.getParameters();
        parameters.put("/c", "Shoppe");
        listcommand.setParameters(parameters);
        assertFalse(listcommand.isValidParameters());
    }

    @Test
    void execute_listTwoInternship_returnsCorrectCommand() {
        ListCommand listcommand = new ListCommand();
        InternshipList internshipList = new InternshipList();
        internshipList.addInternship(internship1);
        internshipList.addInternship(internship2);

        CommandResult result = listcommand.execute(internshipList, new UserProfile());
        assertTrue(result.isSuccessful());
        List<String> feedback = result.getFeedbackToUser();
        assertEquals(6, feedback.size());
        assertEquals(LIST_MESSAGE_SUCCESS, feedback.get(0));
    }

    @Test
    void execute_emptyInternshipList_returnNoInternshipsFound() {
        ListCommand listcommand = new ListCommand();
        InternshipList internshipList = new InternshipList();
        CommandResult result = listcommand.execute(internshipList, userProfile);

        assertTrue(result.isSuccessful());
        assertEquals(List.of("No internships found"), result.getFeedbackToUser());
    }

    @Test
    void execute_onlyHardwareInternshipPresent_outputsCorrectlyIncludingEmptyCategories() {
        ListCommand listcommand = new ListCommand();
        InternshipList internshipList = new InternshipList();
        internshipList.addInternship(internship3);
        CommandResult result = listcommand.execute(internshipList, userProfile);
        List<String> feedback = result.getFeedbackToUser();

        assertTrue(result.isSuccessful());
        assertEquals(5, feedback.size());
        assertEquals(LIST_MESSAGE_SUCCESS, feedback.get(0));
    }
}
