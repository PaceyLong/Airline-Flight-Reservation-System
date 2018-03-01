package csv.parseTypes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FlightParse extends CSVParse {

    @Override
    public JSONArray useCSVLine(String[] strLineArr, JSONArray arr, int i) {
        JSONObject obj = new JSONObject();
        obj.put("originAirport", strLineArr[0]);
        obj.put("destinationAirport", strLineArr[1]);
        obj.put("departureTime", strLineArr[2]);
        obj.put("arrivalTime", strLineArr[3]);
        obj.put("flightNumber", strLineArr[4]);
        obj.put("airfare", strLineArr[5]);
        arr.add(obj);
        return arr;
    }
}
