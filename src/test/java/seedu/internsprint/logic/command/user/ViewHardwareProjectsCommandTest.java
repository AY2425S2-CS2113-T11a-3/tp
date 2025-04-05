package seedu.internsprint.logic.command.user;

import org.junit.jupiter.api.Test;

import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;
import seedu.internsprint.model.userprofile.project.HardwareProject;
import seedu.internsprint.model.userprofile.project.Project;
import seedu.internsprint.util.InternSprintMessages;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ViewHardwareProjectsCommandTest {
    @Test
    void viewHardwareProjects_noProjects_returnsEmptyListMessage() {
        ViewHardwareProjectsCommand viewCommand = new ViewHardwareProjectsCommand();
        UserProfile user = new UserProfile();
        InternshipList internshipList = new InternshipList();

        CommandResult result = viewCommand.execute(internshipList, user);

        assertTrue(result.isSuccessful());
        assertEquals(1, result.getFeedbackToUser().size());
        assertEquals(InternSprintMessages.PROJECTS_VIEW_SUCCESS_MESSAGE, result.getFeedbackToUser().get(0));
    }

    @Test
    void viewHardwareProjects_multipleProjects_returnsAllProjects() {
        ViewHardwareProjectsCommand viewCommand = new ViewHardwareProjectsCommand();
        UserProfile user = new UserProfile();
        InternshipList internshipList = new InternshipList();

        Project project1 = new HardwareProject(
                "Hardware Project 1", "Lead",
                List.of("Component1", "Component2"), "Design and build a circuit",
                "Created a custom PCB using Arduino.", "6 months"
        );
        Project project2 = new HardwareProject(
                "Hardware Project 2", "Member",
                List.of("Component3", "Component4"), "Develop a robot arm",
                "Built a robot arm with motor control.", "3 months"
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
    void viewHardwareProjects_nullUserProfile_throwsException() {
        ViewHardwareProjectsCommand viewCommand = new ViewHardwareProjectsCommand();
        InternshipList internshipList = new InternshipList();

        assertThrows(NullPointerException.class, () -> {
            viewCommand.execute(internshipList, null);
        });
    }
}
