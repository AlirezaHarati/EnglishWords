package utils;

import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSON {

    public Map<String, Object> parse(String json) {
        JSONParser parser = new JSONParser();
        try {
            Map<String, Object> map = (Map) parser.parse(json /*,containerFactory*/);
            return map;
        } catch (ParseException pe) {
            System.out.println("position: " + pe.getPosition());
            System.out.println(pe.getMessage());
        }
        return null;
    }

    public ArrayList<Map<String, Object>> getArrayData(JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.size() == 0) {
            return null;
        }
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            data.add((Map<String, Object>) jsonArray.get(i));
        }

        return data;
    }
}
