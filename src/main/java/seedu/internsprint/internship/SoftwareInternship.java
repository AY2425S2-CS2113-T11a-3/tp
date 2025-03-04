package seedu.internsprint.internship;

public class SoftwareInternship extends Internship {
    private String techStack;

    public SoftwareInternship(String companyName, String role, String techStack) {
        super(companyName, role);
        this.techStack = techStack;
    }

    public void setTechStack(String techStack) {
        this.techStack = techStack;
    }

    @Override
    public String toString() {
        return "Company: " + companyName + ", Role: " + role+ ", Tech: " + techStack;
    }

    @Override
    public String getType() {
        return "software";
    }
}
