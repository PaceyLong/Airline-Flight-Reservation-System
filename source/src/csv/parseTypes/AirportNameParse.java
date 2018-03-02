package csv.parseTypes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
    This parse type parses the lines from 'airports.csv' (which contains the name and the code of each airport)
    and adds them to slots in the corresponding airports json array
 */
public class AirportNameParse extends CSVParse {

    @Override
    public JSONArray useCSVLine(String[] strLineArr, JSONArray arr, int i) {
        JSONObject obj = new JSONObject();
        obj.put("code", strLineArr[0]);
        obj.put("name", strLineArr[1]);
        arr.add(obj);
        return arr;
    }
}
