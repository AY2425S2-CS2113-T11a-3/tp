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

    public GeneralInternship(String companyName, String role, String department, String eligibility,
                             String description, String status, String expectations) {
        super(companyName, role, eligibility,description,status,expectations);
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
        String internshipString =  "Company: " + companyName + ", Role: " + role + ", Dept: " + department;
        if (eligibility != null && !eligibility.isBlank()) {
            internshipString += ", Eligibility: "+eligibility;
        }
        if (description != null && !description.isBlank()) {
            internshipString += ", Description: "+description;
        }
        if (status != null && !status.isBlank()) {
            internshipString += ", Status: "+status;
        }
        if (expectations != null && !expectations.isBlank()) {
            internshipString += ", Expectations: "+expectations;
        }
        return internshipString;
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

    public static GeneralInternship fromJson(JSONObject json) {
        return new GeneralInternship(
                json.getString("companyName"),
                json.getString("role"),
                json.getString("department")
        );
    }

    @Override
    public String getType() {
        return "general";
    }

    public String getDepartment() {
        return department;
    }
}
