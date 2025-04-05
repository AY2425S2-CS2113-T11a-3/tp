package seedu.internsprint.logic.command.internship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;
import seedu.internsprint.util.InternSprintExceptionMessages;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class DeleteCommandTest {

    InternshipList internshipList = new InternshipList();
    UserProfile user = new UserProfile();
    AddGeneralInternshipCommand addGeneralCommand1 = new AddGeneralInternshipCommand();
    AddGeneralInternshipCommand addGeneralCommand2 = new AddGeneralInternshipCommand();

    @BeforeEach
    void setUp() {
        HashMap<String, String> parameters1 = addGeneralCommand1.getParameters();
        parameters1.put("/c", "Google");
        parameters1.put("/r", "Software Engineer");
        parameters1.put("/dept", "Engineering");
        addGeneralCommand1.setParameters(parameters1);
        addGeneralCommand1.execute(internshipList, user);

        HashMap<String, String> parameters2 = addGeneralCommand2.getParameters();
        parameters2.put("/c", "JP Morgan");
        parameters2.put("/r", "Data Engineer");
        parameters2.put("/dept", "Engineering");
        addGeneralCommand2.setParameters(parameters2);
        addGeneralCommand2.execute(internshipList, user);
    }

    @Test
    void deleteCommand_provideCorrectIndex_returnsValid() {
        DeleteCommand deleteCommand = new DeleteCommand();
        HashMap<String, String> parameters = deleteCommand.getParameters();
        parameters.put("/index", "1");
        assertTrue(deleteCommand.isValidParameters());
    }

    @Test
    void deleteCommand_provideMissingIndex_returnsInvalid() {
        DeleteCommand deleteCommand = new DeleteCommand();
        CommandResult result = deleteCommand.execute(new InternshipList(),new UserProfile());
        assertFalse(deleteCommand.isValidParameters());
        assertFalse(result.isSuccessful());
    }

    @Test
    void deleteCommand_provideInvalidIndex_returnsInvalid() {
        DeleteCommand deleteCommand = new DeleteCommand();
        HashMap<String, String> parameters = deleteCommand.getParameters();
        parameters.put("/index", "-1");
        deleteCommand.setParameters(parameters);
        CommandResult result = deleteCommand.execute(new InternshipList(),new UserProfile());
        assertEquals(InternSprintExceptionMessages.INVALID_INDEX_RANGE, result.getFeedbackToUser().get(0));
        assertFalse(result.isSuccessful());
    }

    @Test
    void deleteCommand_provideOutOfBoundsIndex_returnsInvalid() {
        DeleteCommand deleteCommand = new DeleteCommand();
        HashMap<String, String> parameters = deleteCommand.getParameters();
        parameters.put("/index", "3");
        deleteCommand.setParameters(parameters);
        CommandResult result = deleteCommand.execute(internshipList, user);
        assertEquals(InternSprintExceptionMessages.INVALID_INDEX_RANGE, result.getFeedbackToUser().get(0));
        assertFalse(result.isSuccessful());
    }

    @Test
    void deleteCommand_deleteFromEmptyList_returnsInvalid() {
        DeleteCommand deleteCommand = new DeleteCommand();
        HashMap<String, String> parameters = deleteCommand.getParameters();
        parameters.put("/index", "1");
        deleteCommand.setParameters(parameters);
        InternshipList emptyInternshipList = new InternshipList();
        CommandResult result = deleteCommand.execute(emptyInternshipList, user);
        assertEquals(InternSprintExceptionMessages.INVALID_INDEX_RANGE, result.getFeedbackToUser().get(0));
        assertFalse(result.isSuccessful());
    }


}

