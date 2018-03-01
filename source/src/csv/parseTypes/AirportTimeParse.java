package csv.parseTypes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AirportTimeParse extends CSVParse {

    @Override
    public JSONArray useCSVLine(String[] strLineArr, JSONArray arr, int i) {
        JSONObject obj = (JSONObject) arr.get(i);
        obj.put("minConnectionTime", strLineArr[1]);
        return arr;
    }
}
