package csv.parseTypes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/*
    This parse type parses the lines from 'delay.csv' (which contains the code and the delay of each airport)
    and adds them to slots in the corresponding airports json array
 */
public class AirportDelayParse extends CSVParse {

    @Override
    public JSONArray useCSVLine(String[] strLineArr, JSONArray arr, int i) {
        JSONObject obj = (JSONObject) arr.get(i);
        obj.put("delay", strLineArr[1]);
        return arr;
    }
}
