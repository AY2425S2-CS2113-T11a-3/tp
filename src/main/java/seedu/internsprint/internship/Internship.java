package seedu.internsprint.internship;

import org.json.JSONObject;

public abstract class Internship {
    protected String companyName;
    protected String role;

    public Internship(String companyName, String role) {
        this.companyName = companyName;
        this.role = role;
    }

    @Override
    public abstract String toString();

    public abstract JSONObject toJson();

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public abstract String getType();
}
