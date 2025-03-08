package seedu.internsprint.internship;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

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
    public JSONObject toJson() {
        Map<String, Object> orderedMap = new LinkedHashMap<>();
        orderedMap.put("type", "software");
        orderedMap.put("companyName", companyName);
        orderedMap.put("role", role);
        orderedMap.put("techStack", techStack);
        return new JSONObject(orderedMap);
    }

    @Override
    public String getType() {
        return "software";
    }
}
