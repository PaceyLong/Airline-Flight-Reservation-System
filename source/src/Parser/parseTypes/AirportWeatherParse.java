package Parser.parseTypes;

import Airports.Airport;
import Airports.AirportsDB;
import Airports.Weather;

/**
  *  This parse type parses the lines from 'weather.Parser' (which contains the code and a variable number of weather
  *  fields for each airport) and adds them to slots in the corresponding airports HashMap
  */
public class AirportWeatherParse extends CSVParse {

    private static final int AIRPORT_CODE = 0;
    private static final int WEATHER_CONDITION = 1;
    private static final int WEATHER_TEMPERATURE = 2;

    @Override
    public void useCSVLine(String[] strLineArr) {

        //extract values from string array
        String code = strLineArr[AIRPORT_CODE];
        Airport airport = AirportsDB.getInstance().getAirport(code);

        //create and add all weather objects to given airport object
        for(int x=0; x < strLineArr.length-1; x+=2){
            String condition = strLineArr[x+WEATHER_CONDITION];
            int temp = Integer.parseInt(strLineArr[x+WEATHER_TEMPERATURE]);
            Weather weather = new Weather(temp, condition);
            airport.addWeather(weather);
        }
    }
}
