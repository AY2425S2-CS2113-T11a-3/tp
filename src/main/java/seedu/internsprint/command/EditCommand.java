package seedu.internsprint.command;

import seedu.internsprint.internship.*;

import java.util.*;

import static seedu.internsprint.util.InternSprintMessages.*;

public class EditCommand extends Command {
    public static final String[] OPTIONAL_PARAMETERS = {"/c", "/r", "/dept", "/eli", "/ex", "/tech", "/desc", "/hardtech"};


    @Override
    protected boolean isValidParameters() {
        if (!parameters.containsKey("/index")) {
            return false;
        }
        for (String key : parameters.keySet()) {
            if (!key.equals("/index") && !Arrays.asList(OPTIONAL_PARAMETERS).contains(key)) {
                System.out.println("Invalid key found: " + key);
                return false;
            }
        }
        return true;
    }

    @Override
    public CommandResult execute(InternshipList internships) {
        CommandResult result;
        if (!isValidParameters()) {
            result = new CommandResult(invalidParamsMessage());
            result.setSuccessful(false);
            return result;
        }
        Internship foundInternship = null;
        HashMap<String, ArrayList<Internship>> internshipMap = internships.getInternshipMap();
        int index = Integer.parseInt(parameters.get("/index")) - 1;
        boolean wrongTypeOfInternship = false;

        for (Map.Entry<String, ArrayList<Internship>> entry : internshipMap.entrySet()) {
            ArrayList<Internship> oneTypeInternships = entry.getValue();
            if (index >= 0 && index < oneTypeInternships.size()) {
                foundInternship = oneTypeInternships.get(index); // Found the internship

                if (parameters.containsKey("/c")) {
                    foundInternship.setCompanyName(parameters.get("/c"));
                }
                if (parameters.containsKey("/r")) {
                    foundInternship.setRole(parameters.get("/r"));
                }
                if (parameters.containsKey("/dept") && foundInternship instanceof GeneralInternship) {
                    ((GeneralInternship) foundInternship).setDepartment(parameters.get("/dept"));
                } else if (parameters.containsKey("/dept") && !(foundInternship instanceof GeneralInternship)) {
                    wrongTypeOfInternship = true;
                }
                if (parameters.containsKey("/tech") && foundInternship instanceof SoftwareInternship) {
                    ((SoftwareInternship) foundInternship).setTechStack(parameters.get("/tech"));
                } else if (parameters.containsKey("/tech") && !(foundInternship instanceof SoftwareInternship)) {
                    wrongTypeOfInternship = true;
                }
                if (parameters.containsKey("/hardtech") && foundInternship instanceof HardwareInternship) {
                    ((HardwareInternship) foundInternship).setEmbeddedSystems(parameters.get("/hardtech"));
                } else if (parameters.containsKey("/hardtech") && !(foundInternship instanceof HardwareInternship)) {
                    wrongTypeOfInternship = true;
                }
            }
            index -= oneTypeInternships.size();
        }

        if (foundInternship == null || wrongTypeOfInternship) {
            result = new CommandResult(cannotFindInternship());
            result.setSuccessful(false);
            return result;
        }
        List<String> feedback = new ArrayList<>();
        feedback.add(EDIT_MESSAGE_SUCCESS);
        feedback.add(foundInternship.toString());
        result = new CommandResult(feedback);
        result.setSuccessful(true);
        return result;
    }


    protected String invalidParamsMessage() {
        return ("""
                    You have not entered a valid parameter to edit. The edit command works for the following optional parameters:
                Parameters: /c COMPANY_NAME /r ROLE /ex EXPECTATIONS /eli ELIGIBILITY\
                /dept DEPARTMENT /hardtech HARDWARE TECHNOLOGIES /desc DESCRIPTION /tech TECHNOLOGIES
                Example:  /c Google /r Hardware Engineer /tech C, C++""");
    }

    protected String cannotFindInternship() {
        return ("""
                     This internship is not found within your saved list. Check that /index is provided with a valid
                 index reference, or that the type of internship you are editing contains that type of flag""");
    }
}
