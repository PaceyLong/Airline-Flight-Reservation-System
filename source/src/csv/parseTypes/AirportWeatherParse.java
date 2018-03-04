package csv.parseTypes;

import Airports.Airport;
import Airports.Weather;

import java.util.HashMap;

/*
    This parse type parses the lines from 'weather.csv' (which contains the code and a variable number of weather
    fields for each airport) and adds them to slots in the corresponding airports HashMap
 */
public class AirportWeatherParse extends CSVParse {

    @Override
    public HashMap useCSVLine(String[] strLineArr, HashMap hash) {
        //extract values from string array
        String code = strLineArr[0];
        Airport airport = (Airport) hash.get(code);

        //create and add all weather objects to given airport object
        for(int x=1; x < strLineArr.length; x+=2){
            String condition = strLineArr[x];
            int temp = Integer.parseInt(strLineArr[x+1]);
            Weather weather = new Weather(temp, condition);
            airport.addWeather(weather);
        }
        hash.put(code, airport);


        return hash;
    }
}
