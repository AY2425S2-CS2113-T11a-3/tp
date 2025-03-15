package seedu.internsprint.command;

import seedu.internsprint.internship.GeneralInternship;
import seedu.internsprint.internship.HardwareInternship;
import seedu.internsprint.internship.Internship;
import seedu.internsprint.internship.InternshipList;
import seedu.internsprint.internship.SoftwareInternship;
import seedu.internsprint.util.InternSprintMessages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static seedu.internsprint.util.InternSprintExceptionMessages.EDIT_INVALID_PARAMS;
import static seedu.internsprint.util.InternSprintExceptionMessages.EDIT_UNABLE_TO_FIND_INTERNSHIP;
import static seedu.internsprint.util.InternSprintMessages.EDIT_MESSAGE_SUCCESS;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = "    " + COMMAND_WORD + ": Edits the parameters of an internship.\n"
            + "     Parameters: " + "/c COMPANY_NAME /r ROLE /ex EXPECTATIONS /eli ELIGIBILITY\n"
            + "     /dept DEPARTMENT /hardtech HARDWARE TECHNOLOGIES /desc DESCRIPTION /tech TECHNOLOGIES\n"
            + "     Example: " + COMMAND_WORD + " /index 1 /c Google /r Hardware Engineer /tech C, C++";
    public static final String[] POSSIBLE_PARAMETERS = {"/c", "/r", "/dept", "/eli",
        "/ex", "/tech", "/desc", "/hardtech"};

    @Override
    protected boolean isValidParameters() {
        if (!parameters.containsKey("/index")) {
            return false;
        }
        for (String key : parameters.keySet()) {
            if (!key.equals("/index") && !Arrays.asList(POSSIBLE_PARAMETERS).contains(key)) {
                System.out.println("Invalid key found: " + key);
                return false;
            }
        }
        return true;
    }

    @Override
    public CommandResult execute(InternshipList internships) {
        CommandResult result;
        List<String> feedback = new ArrayList<>();
        if (!isValidParameters()) {
            feedback.add(EDIT_INVALID_PARAMS);
            feedback.add(MESSAGE_USAGE);
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            return result;
        }

        Internship foundInternship = null;
        HashMap<String, ArrayList<Internship>> internshipMap = internships.getInternshipMap();
        int index = Integer.parseInt(parameters.get("/index")) - 1;
        boolean checkWrongTypeOfInternship = false;

        for (Map.Entry<String, ArrayList<Internship>> entry : internshipMap.entrySet()) {
            ArrayList<Internship> oneTypeInternships = entry.getValue();
            if (index >= 0 && index < oneTypeInternships.size()) {
                foundInternship = oneTypeInternships.get(index); // Found the internship
                checkWrongTypeOfInternship = editParametersForFoundInternships(foundInternship,
                                                                                checkWrongTypeOfInternship);
            }
            index -= oneTypeInternships.size();
        }

        if (foundInternship == null || checkWrongTypeOfInternship) {
            result = new CommandResult(EDIT_UNABLE_TO_FIND_INTERNSHIP);
            result.setSuccessful(false);
            return result;
        }

        try {
            internships.saveInternships();
            feedback.add(InternSprintMessages.SAVE_SUCCESS_MESSAGE);
        } catch (Exception e) {
            feedback.add(e.getMessage());
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            return result;
        }
        feedback.add(EDIT_MESSAGE_SUCCESS);
        feedback.add(foundInternship.toString());
        result = new CommandResult(feedback);
        result.setSuccessful(true);
        return result;
    }

    private boolean editParametersForFoundInternships(Internship foundInternship, boolean checkWrongTypeOfInternship) {
        if (parameters.containsKey("/c")) {
            foundInternship.setCompanyName(parameters.get("/c"));
        }
        if (parameters.containsKey("/r")) {
            foundInternship.setRole(parameters.get("/r"));
        }
        if (parameters.containsKey("/dept") && foundInternship.getType().equals("general")) {
            ((GeneralInternship) foundInternship).setDepartment(parameters.get("/dept"));
        } else if (parameters.containsKey("/dept") && !(foundInternship.getType().equals("general"))) {
            checkWrongTypeOfInternship = true;
        }
        if (parameters.containsKey("/tech") && foundInternship.getType().equals("software")) {
            ((SoftwareInternship) foundInternship).setTechStack(parameters.get("/tech"));
        } else if (parameters.containsKey("/tech") && !(foundInternship.getType().equals("software"))) {
            checkWrongTypeOfInternship = true;
        }
        if (parameters.containsKey("/hardtech") && foundInternship.getType().equals("hardware")) {
            ((HardwareInternship) foundInternship).setEmbeddedSystems(parameters.get("/hardtech"));
        } else if (parameters.containsKey("/hardtech") && !(foundInternship.getType().equals("hardware"))) {
            checkWrongTypeOfInternship = true;
        }
        if (parameters.containsKey("/eli")) {
            foundInternship.setEligibility(parameters.get("/eli"));
        }
        if (parameters.containsKey("/desc")) {
            foundInternship.setDescription(parameters.get("/desc"));
        }
        if (parameters.containsKey("/ex")) {
            foundInternship.setExpectations(parameters.get("/ex"));
        }
        return checkWrongTypeOfInternship;
    }
}
