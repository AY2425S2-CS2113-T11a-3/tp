package seedu.internsprint.logic.command.user;

import org.junit.jupiter.api.Test;

import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;
import seedu.internsprint.model.userprofile.project.GeneralProject;
import seedu.internsprint.model.userprofile.project.Project;
import seedu.internsprint.util.InternSprintMessages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ViewGeneralProjectsCommandTest {
    @Test
    void viewGeneralProjects_noProjects_returnsEmptyListMessage() {
        ViewGeneralProjectsCommand viewCommand = new ViewGeneralProjectsCommand();
        UserProfile user = new UserProfile();
        InternshipList internshipList = new InternshipList();
        CommandResult result = viewCommand.execute(internshipList, user);
        assertTrue(result.isSuccessful());
        assertEquals(1, result.getFeedbackToUser().size());
        assertEquals(InternSprintMessages.PROJECTS_VIEW_SUCCESS_MESSAGE, result.getFeedbackToUser().get(0));
    }

    @Test
    void viewGeneralProjects_multipleProjects_returnsAllProjects() {
        ViewGeneralProjectsCommand viewCommand = new ViewGeneralProjectsCommand();
        UserProfile user = new UserProfile();
        InternshipList internshipList = new InternshipList();

        Project project1 = new GeneralProject(
                "Project 1",
                "Software Developer",
                "Tech Department",
                "Develop a web app",
                "Created a web application using React and Node.js.",
                "3 months"
        );

        Project project2 = new GeneralProject(
                "Project 2",
                "Machine Learning Engineer",
                "AI Department",
                "Train an AI model",
                "Developed a deep learning model for image recognition.",
                "6 months"
        );

        user.projects.addProject(project1);
        user.projects.addProject(project2);
        CommandResult result = viewCommand.execute(internshipList, user);
        assertTrue(result.isSuccessful());
        assertEquals(3, result.getFeedbackToUser().size());
        assertEquals(InternSprintMessages.PROJECTS_VIEW_SUCCESS_MESSAGE, result.getFeedbackToUser().get(0));
        assertEquals(project1.toDescription(), result.getFeedbackToUser().get(1));
        assertEquals(project2.toDescription(), result.getFeedbackToUser().get(2));
    }

    @Test
    void viewGeneralProjects_nullUserProfile_throwsException() {
        ViewGeneralProjectsCommand viewCommand = new ViewGeneralProjectsCommand();
        InternshipList internshipList = new InternshipList();

        assertThrows(NullPointerException.class, () -> {
            viewCommand.execute(internshipList, null);
        });
    }
    
}
