package seedu.internsprint.logic.command.user;

import org.junit.jupiter.api.Test;

import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;
import seedu.internsprint.model.userprofile.project.Project;
import seedu.internsprint.model.userprofile.project.SoftwareProject;
import seedu.internsprint.util.InternSprintMessages;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ViewSoftwareProjectsCommandTest {

    @Test
    void viewSoftwareProjects_noProjects_returnsEmptyListMessage() {
        ViewSoftwareProjectsCommand viewCommand = new ViewSoftwareProjectsCommand();
        UserProfile user = new UserProfile();
        InternshipList internshipList = new InternshipList();
        CommandResult result = viewCommand.execute(internshipList, user);
        assertTrue(result.isSuccessful());
        assertEquals(1, result.getFeedbackToUser().size());
        assertEquals(InternSprintMessages.PROJECTS_VIEW_SUCCESS_MESSAGE, result.getFeedbackToUser().get(0));
    }

    @Test
    void viewSoftwareProjects_multipleProjects_returnsAllProjects() {
        ViewSoftwareProjectsCommand viewCommand = new ViewSoftwareProjectsCommand();
        UserProfile user = new UserProfile();
        InternshipList internshipList = new InternshipList();

        Project project1 = new SoftwareProject(
                "Software Project 1", "Developer",
                List.of("Java", "Python"), "Develop a mobile app",
                "Built a mobile app with React Native.", "6 months"
        );
        Project project2 = new SoftwareProject(
                "Software Project 2", "Tester",
                List.of("JavaScript", "C#"), "Test an API",
                "Tested a RESTful API for various use cases.", "4 months"
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
    void viewSoftwareProjects_nullUserProfile_throwsException() {
        ViewSoftwareProjectsCommand viewCommand = new ViewSoftwareProjectsCommand();
        InternshipList internshipList = new InternshipList();
        assertThrows(NullPointerException.class, () -> {
            viewCommand.execute(internshipList, null);
        });
    }
}
