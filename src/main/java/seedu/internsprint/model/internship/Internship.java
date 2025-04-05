package seedu.internsprint.model.internship;

import seedu.internsprint.exceptions.DuplicateEntryException;
import seedu.internsprint.model.internship.interview.Interview;

import java.util.ArrayList;

import org.json.JSONObject;

import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_REQUIRED_PARAMETERS;

/**
 * Represents an internship.
 */
public abstract class Internship {

    /* Mandatory required parameters to create an internship */
    protected String companyName;
    protected String role;

    /* Optional parameters to create an internship */
    protected String description = null;
    protected String eligibility = null;
    protected String status = null;
    protected String expectations = null;

    protected Interview interview;
    protected int internshipId;

    public Internship(String companyName, String role) {
        if (companyName == null || role == null || companyName.isBlank() || role.isBlank()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/c or /r"));
        }
        this.companyName = companyName;
        this.role = role;
    }

    public Internship(String companyName, String role, String eligibility, String description, String status,
                      String expectations) {
        if (companyName == null || role == null || companyName.isBlank() || role.isBlank()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/c or /r"));
        }
        this.companyName = companyName;
        this.role = role;

        if (eligibility != null && !eligibility.isBlank()) {
            this.eligibility = eligibility;
        }
        if (description != null && !description.isBlank()) {
            this.description = description;
        }
        if (status != null && !status.isBlank()) {
            this.status = status;
        }
        if (expectations != null && !expectations.isBlank()) {
            this.expectations = expectations;
        }
    }

    /**
     * Adds an interview to the internship.
     * If the internship already has an interview, the interview is added as the next round.
     *
     * @param interview Interview to add.
     */
    public void addInterview(Interview interview) throws DuplicateEntryException {
        if (this.interview == null) {
            this.interview = interview;
        } else {
            this.interview.addInterviewRound(interview);
        }
    }

    /**
     * Creates a copy of the internship.
     *
     * @return Copy of the internship.
     */
    public abstract Internship copy();

    /**
     * Returns a string representation of the internship.
     *
     * @return String representation of the internship.
     */
    @Override
    public abstract String toString();

    /**
     * Returns a string representation of the internship.
     * Shows all details of the internship.
     *
     * @return String representation of the internship.
     */
    public ArrayList<String> toDescription() {
        ArrayList<String> internshipString = new ArrayList<>();
        internshipString.add("Company: " + companyName);
        internshipString.add("Role: " + role);
        if (eligibility != null && !eligibility.isBlank()) {
            internshipString.add("Eligibility: " + eligibility);
        }
        if (description != null && !description.isBlank()) {
            internshipString.add("Description: " + description);
        }
        if (status != null && !status.isBlank()) {
            internshipString.add("Status: " + status);
        }
        if (expectations != null && !expectations.isBlank()) {
            internshipString.add("Expectations: " + expectations);
        }
        if (interview != null) {
            internshipString.addAll(interview.toDescription());
        }
        return internshipString;
    }

    /**
     * Returns true if the internship is equal to another object.
     *
     * @param obj Object to compare with.
     * @return True if the internship is equal to the object.
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * Converts the internship to a JSON object.
     *
     * @return JSON object of the internship.
     */
    public abstract JSONObject toJson();

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public abstract String getType();

    public String getCompanyName() {
        return companyName;
    }

    public String getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpectations() {
        return expectations;
    }

    public void setExpectations(String expectations) {
        this.expectations = expectations;
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    public int getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(int internshipId) {
        this.internshipId = internshipId;
    }
}
