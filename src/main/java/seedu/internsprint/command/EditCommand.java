package seedu.internsprint.command;

import seedu.internsprint.handler.CommandParser;
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

import static seedu.internsprint.util.InternSprintExceptionMessages.EDIT_INVALID_PARAMS;
import static seedu.internsprint.util.InternSprintExceptionMessages.EDIT_UNABLE_TO_FIND_INTERNSHIP;
import static seedu.internsprint.util.InternSprintMessages.EDIT_MESSAGE_SUCCESS;
import static seedu.internsprint.util.InternSprintMessages.MESSAGE_DUPLICATE_INTERNSHIP;

import java.util.logging.Logger;
import java.util.logging.Level;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the parameters of an internship.\n"
            + "    Parameters: " + "/c COMPANY_NAME /r ROLE /ex EXPECTATIONS /eli ELIGIBILITY\n"
            + "    /dept DEPARTMENT /hardtech HARDWARE TECHNOLOGIES /desc DESCRIPTION /tech TECHNOLOGIES\n"
            + "    Example: " + COMMAND_WORD + " /index 1 /c Google /r Hardware Engineer /tech C, C++";
    public static final String[] POSSIBLE_PARAMETERS = {"/c", "/r", "/dept", "/eli",
        "/ex", "/tech", "/desc", "/hardtech"};
    private static final Logger logger = Logger.getLogger(EditCommand.class.getName());

    @Override
    protected boolean isValidParameters() {
        logger.log(Level.INFO, "Entering check for parameters in edit command.");
        if (!parameters.containsKey("/index")) {
            logger.log(Level.WARNING, "There is no specified index.");
            return false;
        }
        assert  parameters.containsKey("/index"): "/index flag should be present in the edit command";
        for (String key : parameters.keySet()) {
            if (!key.equals("/index") && !Arrays.asList(POSSIBLE_PARAMETERS).contains(key)) {
                logger.log(Level.WARNING, "There is a flag that is out of specified optional parameters.");
                System.out.println("Invalid key found: " + key);
                return false;
            }
        }
        assert parameters.keySet().stream().allMatch(key -> key.equals("/index")
                                                    || Arrays.asList(POSSIBLE_PARAMETERS).contains(key))
                : "All flags should be members of set of predefined valid flags";
        return true;
    }

    @Override
    public CommandResult execute(InternshipList internships) {
        logger.log(Level.INFO, "Entering execute for edit command...");
        CommandResult result;
        List<String> feedback = new ArrayList<>();
        if (!isValidParameters()) {
            logger.log(Level.WARNING, "There are invalid parameters so error result is output to user.");
            feedback.add(EDIT_INVALID_PARAMS);
            feedback.add(MESSAGE_USAGE);
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            return result;
        }

        HashMap<String, ArrayList<Internship>> internshipMap = internships.getInternshipMap();

        String[] validIndex;
        try {
            validIndex = CommandParser.validateIndex(parameters.get("/index"), internships);
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Index for edit command out of range...");
            feedback.add(e.getMessage());
            result = new CommandResult(feedback);
            result.setSuccessful(false);
            return result;
        }

        int index = Integer.parseInt(validIndex[1]);
        String type = validIndex[0];
        assert  (index>=0 && index< internships.getInternshipCount()): "index value should be within appropriate range";

        Internship foundInternship = internshipMap.get(type).get(index);
        Internship foundInternshipCopy = foundInternship.copy();
        boolean checkWrongTypeOfInternship = editParametersForFoundInternships(foundInternship);

        if (foundInternship == null || checkWrongTypeOfInternship) {
            logger.log(Level.WARNING, "Internship not found");
            result = new CommandResult(EDIT_UNABLE_TO_FIND_INTERNSHIP);
            result.setSuccessful(false);
            return result;
        }

        long count = internshipMap.values().stream().flatMap(List::stream)
            .filter(internship -> internship.equals(foundInternship))
            .count();
        if (count >= 2) {
            internshipMap.get(type).set(index, foundInternshipCopy);
            feedback.add(MESSAGE_DUPLICATE_INTERNSHIP);
            result = new CommandResult(feedback);
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
        logger.log(Level.INFO, "Finished processing for exit command");
        assert foundInternship!=null: "Internship should not be a null value.";
        feedback.add(EDIT_MESSAGE_SUCCESS);
        feedback.add(String.valueOf(foundInternship.toDescription()));
        result = new CommandResult(feedback);
        result.setSuccessful(true);
        return result;
    }

    private boolean editParametersForFoundInternships(Internship foundInternship) {
        logger.log(Level.INFO, "Editing given parameters...");
        boolean checkWrongTypeOfInternship = false;
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
