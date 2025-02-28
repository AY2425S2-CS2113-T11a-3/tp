package seedu.internsprint.internship;

public abstract class Internship {
    protected String companyName;
    protected String role;

    public Internship(String companyName, String role) {
        this.companyName = companyName;
        this.role = role;
    }

    @Override
    public abstract String toString();

    public abstract String getType();
}
