package seedu.internsprint.logic.command.user;

import org.junit.jupiter.api.Test;

import seedu.internsprint.logic.command.CommandResult;
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
        parameters.put("/pay", "1000-2000");
        parameters.put("/ind", "Tech");
        parameters.put("/mgoals", "Be rich");
        parameters.put("/ygoals", "Be richer");
        parameters.put("/c", "Google");
        userProfileCommand.setParameters(parameters);
        userProfileCommand.execute(new InternshipList(), user);
        assertEquals("Nikita", user.getName());
        assertEquals("Be rich", user.getMonthlyGoals());
        assertEquals("1000.0 - 2000.0", user.getTargetStipendRange());
    }
    @Test
    void execute_wordStipend_returnsInvalid() {
        UserProfile user = new UserProfile();
        UserProfileCommand userProfileCommand = new UserProfileCommand();
        HashMap<String, String> parameters = userProfileCommand.getParameters();
        parameters.put("/name", "Nikita");
        parameters.put("/pay", "this-that");
        userProfileCommand.setParameters(parameters);
        CommandResult result = userProfileCommand.execute(new InternshipList(), user);
        assertFalse(result.isSuccessful());
    }
    @Test
    void execute_minMoreThanMaxStipend_returnsInvalid() {
        UserProfile user = new UserProfile();
        UserProfileCommand userProfileCommand = new UserProfileCommand();
        HashMap<String, String> parameters = userProfileCommand.getParameters();
        parameters.put("/name", "Nikita");
        parameters.put("/pay", "2000-1");
        userProfileCommand.setParameters(parameters);
        CommandResult result = userProfileCommand.execute(new InternshipList(), user);
        assertFalse(result.isSuccessful());
    }
    @Test
    void execute_twoDPChecks_updatesCorrectly() {
        UserProfile user = new UserProfile();
        UserProfileCommand userProfileCommand = new UserProfileCommand();
        HashMap<String, String> parameters = userProfileCommand.getParameters();
        parameters.put("/name", "Nikita");
        parameters.put("/pay", "2000.00327983-30000.8924789");
        userProfileCommand.setParameters(parameters);
        userProfileCommand.execute(new InternshipList(), user);
        assertEquals("2000.0 - 30000.89", user.getTargetStipendRange());
    }

}
