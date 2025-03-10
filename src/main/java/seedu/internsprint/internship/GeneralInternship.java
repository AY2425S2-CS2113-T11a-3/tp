package seedu.internsprint.internship;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import static seedu.internsprint.util.InternSprintExceptionMessages.MISSING_REQUIRED_PARAMETERS;

public class GeneralInternship extends Internship {
    private String department;

    public GeneralInternship(String companyName, String role, String department) {
        super(companyName, role);
        if (department == null || department.isBlank()) {
            throw new IllegalArgumentException(String.format(MISSING_REQUIRED_PARAMETERS, "/dept"));
        }
        this.department = department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Company: " + companyName + ", Role: " + role + ", Dept: " + department;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        GeneralInternship generalInternship = (GeneralInternship) obj;
        return companyName.equals(generalInternship.getCompanyName())
                && role.equals(generalInternship.getRole())
                && department.equals(generalInternship.getDepartment());
    }

    @Override
    public JSONObject toJson() {
        Map<String, Object> orderedMap = new LinkedHashMap<>();
        orderedMap.put("type", "general");
        orderedMap.put("companyName", companyName);
        orderedMap.put("role", role);
        orderedMap.put("department", department);
        return new JSONObject(orderedMap);
    }

    @Override
    public String getType() {
        return "general";
    }

    public String getDepartment() {
        return department;
    }
}
