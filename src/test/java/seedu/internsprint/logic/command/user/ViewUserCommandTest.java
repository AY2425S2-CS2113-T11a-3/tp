package seedu.internsprint.logic.command.user;

import org.junit.jupiter.api.Test;

import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ViewUserCommandTest {

    @Test
    void execute_view_outputsCorrectly() {
        UserProfile user = new UserProfile();
        ViewUserCommand viewUserCommand = new ViewUserCommand();
        HashMap<String, String> parameters = viewUserCommand.getParameters();
        parameters.put("/name", "Nikita");
        parameters.put("/pay", "1000");
        parameters.put("/ind", "Tech");
        parameters.put("/mgoals", "Be rich");
        parameters.put("/ygoals", "Be richer");
        parameters.put("/c", "Google");
        viewUserCommand.setParameters(parameters);
        viewUserCommand.execute(new InternshipList(), user);
        String output = user.toExtendedString();
        assertNotNull(output);
    }
}
