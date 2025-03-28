package seedu.internsprint.logic.command.internship;

import org.junit.jupiter.api.Test;

import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.internship.GeneralInternship;
import seedu.internsprint.model.internship.HardwareInternship;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.internship.SoftwareInternship;
import seedu.internsprint.model.userprofile.UserProfile;
import seedu.internsprint.util.InternSprintExceptionMessages;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internsprint.util.InternSprintMessages.DESC_MESSAGE_SUCCESS;

public class DescriptionCommandTest {
    @Test
    void isValidParameters_provideValidIndex_returnsTrue() {
        DescriptionCommand descriptioncommand = new DescriptionCommand();
        HashMap<String, String> parameters = descriptioncommand.getParameters();
        parameters.put("/index", "1");
        descriptioncommand.setParameters(parameters);
        assertTrue(descriptioncommand.isValidParameters());
    }

    @Test
    void isValidParameters_provideInvalidIndex_returnsFalse() {
        DescriptionCommand descriptioncommand = new DescriptionCommand();
        HashMap<String, String> parameters = descriptioncommand.getParameters();
        parameters.put("/index", "-1");
        descriptioncommand.setParameters(parameters);
        CommandResult commandResult = descriptioncommand.execute(new InternshipList(), new UserProfile());
        assertEquals(InternSprintExceptionMessages.INVALID_INDEX_RANGE,
                commandResult.getFeedbackToUser().get(0));
        assertFalse(commandResult.isSuccessful());
    }

    @Test
    void execute_descriptionOfIndex2Internship_returnsCorrectCommand() {
        DescriptionCommand descriptioncommand = new DescriptionCommand();
        HashMap<String, String> parameters = descriptioncommand.getParameters();
        parameters.put("/index", "2");
        descriptioncommand.setParameters(parameters);

        HardwareInternship internship1 = new HardwareInternship("HP", "Manufacturing Engineer",
                "Embedded circuit design");
        GeneralInternship internship2 = new GeneralInternship("LG", "Project Manager",
                "Resource Department");
        SoftwareInternship internship3 = new SoftwareInternship("Google", "Software Engineer",
                "Java");
        InternshipList internshipList = new InternshipList();
        internshipList.addInternship(internship1);
        internshipList.addInternship(internship2);
        internshipList.addInternship(internship3);

        CommandResult commandResult = descriptioncommand.execute(internshipList,new UserProfile());
        assertTrue(commandResult.isSuccessful());
        List<String> feedback = commandResult.getFeedbackToUser();
        assertEquals(4, feedback.size());
        assertEquals(DESC_MESSAGE_SUCCESS, feedback.get(0));
    }
}
