package seedu.internsprint.internship;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

public class GeneralInternship extends Internship {
    private String department;

    public GeneralInternship(String companyName, String role, String department) {
        super(companyName, role);
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
}
