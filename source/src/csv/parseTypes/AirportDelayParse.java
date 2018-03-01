package csv.parseTypes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AirportDelayParse extends CSVParse {

    @Override
    public JSONArray useCSVLine(String[] strLineArr, JSONArray arr, int i) {
        JSONObject obj = (JSONObject) arr.get(i);
        obj.put("delay", strLineArr[1]);
        return arr;
    }
}
