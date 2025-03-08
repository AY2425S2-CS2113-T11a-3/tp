package seedu.internsprint.internship;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

public class HardwareInternship extends Internship {
    private String embeddedSystems;

    public HardwareInternship(String companyName, String role, String  embeddedSystems) {
        super(companyName, role);
        this.embeddedSystems = embeddedSystems;
    }

    public void setEmbeddedSystems(String embeddedSystems) {
        this.embeddedSystems = embeddedSystems;
    }

    @Override
    public String toString() {
        return "Company: " + companyName + ", Role: " + role + ", Tech: " + embeddedSystems;
    }

    @Override
    public JSONObject toJson() {
        Map<String, Object> orderedMap = new LinkedHashMap<>();
        orderedMap.put("type", "hardware");
        orderedMap.put("companyName", companyName);
        orderedMap.put("role", role);
        orderedMap.put("embeddedSystems", embeddedSystems);
        return new JSONObject(orderedMap);
    }

    @Override
    public String getType() {
        return "hardware";
    }
}
