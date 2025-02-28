package seedu.internsprint.internship;

public class SoftwareInternship extends Internship {
    private String techStack;

    public SoftwareInternship(String companyName, String role, String techStack) {
        super(companyName, role);
        this.techStack = techStack;
    }

    @Override
    public String toString() {
        return "Company: " + companyName + ", Role: " + role;
    }

    @Override
    public String getType() {
        return "software";
    }
}
