package Parser.parseTypes;
import Airports.Airport;
import Airports.AirportsDB;
import Airports.WeatherList;

/*
    This parse type parses the lines from 'airports.Parser' (which contains the name and the code of each airport)
    and adds them to slots in the corresponding airports HashMap
 */
public class AirportNameParse extends CSVParse {

    private static final int AIRPORT_CODE = 0;
    private static final int AIRPORT_NAME = 1;

    @Override
    public void useCSVLine(String[] strLineArr) {
        //extract values from string array
        String code = strLineArr[AIRPORT_CODE];
        String name = strLineArr[AIRPORT_NAME];

        //create new airport object, add to airport hash
        Airport airport = new Airport(name, code, new WeatherList(), 0, 0);
        AirportsDB.getInstance().addAirport(code, airport);
    }
}
