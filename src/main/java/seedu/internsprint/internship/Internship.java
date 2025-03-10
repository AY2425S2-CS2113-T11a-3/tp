package seedu.internsprint.internship;

import org.json.JSONObject;

import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_REQUIRED_PARAMETERS;

public abstract class Internship {
    protected String companyName;
    protected String role;

    public Internship(String companyName, String role) {
        if (companyName == null || role == null || companyName.isBlank() || role.isBlank()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/c or /r"));
        }
        this.companyName = companyName;
        this.role = role;
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
}
