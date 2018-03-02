package csv.parseTypes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
    This parse type parses the lines from 'min_connection_time.csv' (which contains the code and minimum connection time
    for each airport) and adds them to slots in the corresponding airports json array
 */
public class AirportTimeParse extends CSVParse {

    @Override
    public JSONArray useCSVLine(String[] strLineArr, JSONArray arr, int i) {
        JSONObject obj = (JSONObject) arr.get(i);
        obj.put("minConnectionTime", strLineArr[1]);
        return arr;
    }
}
