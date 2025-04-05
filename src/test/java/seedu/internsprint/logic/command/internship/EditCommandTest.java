package seedu.internsprint.logic.command.internship;

import org.junit.jupiter.api.Test;

import seedu.internsprint.logic.command.CommandResult;
import seedu.internsprint.model.internship.SoftwareInternship;
import seedu.internsprint.model.internship.GeneralInternship;
import seedu.internsprint.model.internship.HardwareInternship;
import seedu.internsprint.model.internship.InternshipList;
import seedu.internsprint.model.userprofile.UserProfile;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.internsprint.util.InternSprintMessages.MESSAGE_DUPLICATE_INTERNSHIP;


class EditCommandTest {

    @Test
    void isValidParameters_provideCorrectIndexAndFlags_returnsValid() {
        EditCommand editCommand = new EditCommand();
        editCommand.getParameters().put("/index", "1");
        editCommand.getParameters().put("/c", "Java");
        editCommand.getParameters().put("/eli", "Y3 students");
        editCommand.getParameters().put("/r","/Automation Testing Intern");
        editCommand.getParameters().put("/tech", "Java, C, C++");
        editCommand.getParameters().put("/dept","Quality Assurance");
        assertTrue(editCommand.isValidParameters());
    }
    @Test
    void isValidParameters_provideNoIndex_returnsValid() {
        EditCommand editCommand = new EditCommand();
        editCommand.getParameters().put("/c", "Java");
        editCommand.getParameters().put("/eli", "Y3 students");
        editCommand.getParameters().put("/r","/Automation Testing Intern");
        editCommand.getParameters().put("/tech", "Java, C, C++");
        editCommand.getParameters().put("/dept","Quality Assurance");
        assertTrue(editCommand.isValidParameters());
    }
    @Test
    void isValidParameters_provideInvalidFlag_returnsInvalid() {
        EditCommand editCommand = new EditCommand();
        editCommand.getParameters().put("/c", "Java");
        editCommand.getParameters().put("/eli", "Y3 students");
        editCommand.getParameters().put("/r","/Automation Testing Intern");
        editCommand.getParameters().put("/tech", "Java, C, C++");
        editCommand.getParameters().put("/deadline","27th January");
        assertFalse(editCommand.isValidParameters());
    }


    @Test
    void execute_editCompanyAndRoleForSoftwareInternships_editsCorrectly() {
        EditCommand editCommand = new EditCommand();
        editCommand.getParameters().put("/index", "1");
        editCommand.getParameters().put("/c", "Java");
        editCommand.getParameters().put("/r","Automation Testing Intern");
        SoftwareInternship internship = new SoftwareInternship("Facebook","Software Engineering", "SWE");
        InternshipList internshipList = new InternshipList();
        assertDoesNotThrow(() -> internshipList.addInternship(internship));
        editCommand.execute(internshipList, new UserProfile());
        assertEquals("Java", internship.getCompanyName());
        assertEquals("Automation Testing Intern", internship.getRole());
        assertEquals("software", internship.getType());

    }

    @Test
    void execute_editCompanyAndRoleForGeneralInternships_editsCorrectly() {
        EditCommand editCommand = new EditCommand();
        editCommand.getParameters().put("/index", "1");
        editCommand.getParameters().put("/c", "UBS");
        editCommand.getParameters().put("/r","IT Intern");
        GeneralInternship internship = new GeneralInternship("Facebook","Tech Support", "IT");
        InternshipList internshipList = new InternshipList();
        assertDoesNotThrow(() -> internshipList.addInternship(internship));
        editCommand.execute(internshipList, new UserProfile());
        assertEquals("UBS", internship.getCompanyName());
        assertEquals("IT Intern", internship.getRole());
        assertEquals("general", internship.getType());
    }
    @Test
    void execute_editCompanyAndRoleForHarwareInternships_editsCorrectly() {
        EditCommand editCommand = new EditCommand();
        editCommand.getParameters().put("/index", "1");
        editCommand.getParameters().put("/c", "Xilinx");
        editCommand.getParameters().put("/r","Engineering Intern");
        HardwareInternship internship = new HardwareInternship("Facebook","Automation Expert", "C");
        InternshipList internshipList = new InternshipList();
        assertDoesNotThrow(() -> internshipList.addInternship(internship));
        editCommand.execute(internshipList, new UserProfile());
        assertEquals("Xilinx", internship.getCompanyName());
        assertEquals("Engineering Intern", internship.getRole());
        assertEquals("hardware", internship.getType());
    }

    @Test
    void execute_invalidFieldForSoftware_throwsError() {
        EditCommand editCommand = new EditCommand();
        editCommand.getParameters().put("/index", "1");
        editCommand.getParameters().put("/c", "Java");
        editCommand.getParameters().put("/dept","SWE Intern");
        SoftwareInternship internship = new SoftwareInternship("Facebook","Automation Intern", "C");
        InternshipList internshipList = new InternshipList();
        assertDoesNotThrow(() -> internshipList.addInternship(internship));
        editCommand.execute(internshipList, new UserProfile());
        assertEquals("Java", internship.getCompanyName());
        assertEquals("Automation Intern", internship.getRole());
        assertEquals("C", internship.getTechStack());
        assertEquals("software", internship.getType());
    }

    @Test
    void isPresentIndex_provideNoIndex_returnsInvalid() {
        EditCommand editCommand = new EditCommand();
        HashMap<String, String> parameters = editCommand.getParameters();
        parameters.put("/c", "Java");
        editCommand.setParameters(parameters);
        assertFalse(editCommand.isPresentIndex());
    }

    @Test
    void isPresentIndex_provideIndex_returnsValid() {
        EditCommand editCommand = new EditCommand();
        HashMap<String, String> parameters = editCommand.getParameters();
        parameters.put("/index", "2");
        parameters.put("/eli", "Y3 students");
        editCommand.setParameters(parameters);
        assertTrue(editCommand.isPresentIndex());
    }

    @Test
    void execute_multipleInternships_editsCorrectly() {
        EditCommand editCommand = new EditCommand();
        HashMap<String, String> parameters = editCommand.getParameters();
        parameters.put("/index", "2");
        parameters.put("/eli", "Good knowledge of microcontrollers");
        editCommand.setParameters(parameters);
        SoftwareInternship internship = new SoftwareInternship("Facebook", "Automation Intern", "C");
        HardwareInternship internship2 = new HardwareInternship("AMD", "Engineer", "Arduino");
        InternshipList internshipList = new InternshipList();
        assertDoesNotThrow(() -> internshipList.addInternship(internship));
        assertDoesNotThrow(() -> internshipList.addInternship(internship2));
        editCommand.execute(internshipList, new UserProfile());
        assertEquals("Good knowledge of microcontrollers", internship2.getEligibility());
        assertEquals("AMD", internship2.getCompanyName());
    }

    @Test
    void execute_editInternshipIsDuplicate_returnsError() {
        EditCommand editCommand = new EditCommand();
        HashMap<String, String> parameters = editCommand.getParameters();
        parameters.put("/index", "1");
        parameters.put("/c", "Google");
        parameters.put("/r", "SDE Intern");
        editCommand.setParameters(parameters);
        SoftwareInternship internship = new SoftwareInternship("Facebook", "Automation Intern", "C");
        SoftwareInternship internship2 = new SoftwareInternship("Google", "SDE Intern", "C");
        InternshipList internshipList = new InternshipList();
        assertDoesNotThrow(() -> internshipList.addInternship(internship));
        assertDoesNotThrow(() -> internshipList.addInternship(internship2));
        CommandResult result = editCommand.execute(internshipList, new UserProfile());
        assertFalse(result.isSuccessful());
        assertEquals(MESSAGE_DUPLICATE_INTERNSHIP, result.getFeedbackToUser().get(0));
        assertEquals("Facebook", internshipList.getInternshipMap().get("software").get(0).getCompanyName());
        assertEquals("Automation Intern", internshipList.getInternshipMap().get("software").get(0).getRole());
    }

    @Test
    void execute_provideCorrectOptionalFlags_editsCorrectly() {
        EditCommand editCommand = new EditCommand();
        HashMap<String, String> parameters = editCommand.getParameters();
        parameters.put("/index", "1");
        parameters.put("/eli", "Year 3 students");
        parameters.put("/ex", "Should be fast learner");
        parameters.put("/status", "Stage 1");
        editCommand.setParameters(parameters);
        SoftwareInternship internship = new SoftwareInternship("Facebook", "Software Engineering",
                "SWE");
        InternshipList internshipList = new InternshipList();
        assertDoesNotThrow(() -> internshipList.addInternship(internship));
        editCommand.execute(internshipList, new UserProfile());
        assertEquals("Year 3 students", internship.getEligibility());
        assertEquals("Should be fast learner", internship.getExpectations());
        assertEquals("Stage 1", internship.getStatus());
    }
}
