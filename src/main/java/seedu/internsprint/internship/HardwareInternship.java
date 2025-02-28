package seedu.internsprint.internship;

public class HardwareInternship extends Internship {
    private String embeddedSystems;

    public HardwareInternship(String companyName, String role, String  embeddedSystems) {
        super(companyName, role);
        this.embeddedSystems = embeddedSystems;
    }

    @Override
    public String toString() {
        return "Company: " + companyName + ", Role: " + role;
    }

    @Override
    public String getType() {
        return "hardware";
    }
}
