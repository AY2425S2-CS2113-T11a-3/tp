package seedu.internsprint.internship;

import org.json.JSONObject;

import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_REQUIRED_PARAMETERS;

public abstract class Internship {

    //Mandatory required parameters to create an internship
    protected String companyName;
    protected String role;

    //Optional parameters to create an internship
    protected String description = null;
    protected String eligibility = null;
    protected String status = null;
    protected String expectations = null;

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


    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object obj);

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
}
