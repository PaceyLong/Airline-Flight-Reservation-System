package csv.parseTypes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AirportWeatherParse extends CSVParse {

    @Override
    public JSONArray useCSVLine(String[] strLineArr, JSONArray arr, int i) {
        JSONObject obj = (JSONObject) arr.get(i);
        JSONArray weatherArr = new JSONArray();
        for(int x=1; x < strLineArr.length; x+=2){
            JSONObject weatherObj = new JSONObject();
            weatherObj.put("condition", strLineArr[x]);
            weatherObj.put("temp", strLineArr[x+1]);
            weatherArr.add(weatherObj);
        }
        obj.put("weather", weatherArr);

        return arr;
    }
}
