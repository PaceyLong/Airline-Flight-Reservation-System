package csv.parseTypes;

import Airports.Airport;
import Airports.AirportsDB;
import Airports.Weather;

/*
    This parse type parses the lines from 'weather.csv' (which contains the code and a variable number of weather
    fields for each airport) and adds them to slots in the corresponding airports HashMap
 */
public class AirportWeatherParse extends CSVParse {

    @Override
    public void useCSVLine(String[] strLineArr) {
        //extract values from string array
        String code = strLineArr[0];
        Airport airport = AirportsDB.getInstance().getAirport(code);

        //create and add all weather objects to given airport object
        for(int x=1; x < strLineArr.length; x+=2){
            String condition = strLineArr[x];
            int temp = Integer.parseInt(strLineArr[x+1]);
            Weather weather = new Weather(temp, condition);
            airport.addWeather(weather);
        }
    }
}
