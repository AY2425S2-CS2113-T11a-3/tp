package seedu.internsprint.logic.command.user;

import org.junit.jupiter.api.Test;

import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserProfileCommandTest {


    @Test
    void isValidParameters_provideCorrectFlags_returnsValid() {
        UserProfileCommand userProfileCommand = new UserProfileCommand();
        HashMap<String, String> parameters = userProfileCommand.getParameters();
        parameters.put("/name", "Nikita");
        parameters.put("/pay", "1000");
        parameters.put("/ind", "Tech");
        parameters.put("/mgoals", "Be rich");
        parameters.put("/ygoals", "Be richer");
        parameters.put("/c", "Google");
        userProfileCommand.setParameters(parameters);
        assertTrue(userProfileCommand.isValidParameters());
    }
    @Test
    void isValidParameters_provideIncorrectFlags_returnsInvalid() {
        UserProfileCommand userProfileCommand = new UserProfileCommand();
        HashMap<String, String> parameters = userProfileCommand.getParameters();
        parameters.put("/fullname", "Nikita");
        parameters.put("/pay", "1000");
        parameters.put("/ind", "Tech");
        parameters.put("/mgoals", "Be rich");
        parameters.put("/ygoals", "Be richer");
        parameters.put("/c", "Google");
        userProfileCommand.setParameters(parameters);
        assertFalse(userProfileCommand.isValidParameters());
    }

    @Test
    void execute_updateField_updatesCorrectly() {
        UserProfile user = new UserProfile();
        UserProfileCommand userProfileCommand = new UserProfileCommand();
        HashMap<String, String> parameters = userProfileCommand.getParameters();
        parameters.put("/name", "Nikita");
        parameters.put("/pay", "1000");
        parameters.put("/ind", "Tech");
        parameters.put("/mgoals", "Be rich");
        parameters.put("/ygoals", "Be richer");
        parameters.put("/c", "Google");
        userProfileCommand.setParameters(parameters);
        userProfileCommand.execute(new InternshipList(), user);
        assertEquals("Nikita", user.getName());
        assertEquals("Be rich", user.getMonthlyGoals());
        assertEquals("1000", user.getTargetStipendRange());
    }

}
