package Parser.parseTypes;
import Airports.Airport;
import Airports.AirportsDB;

/*
    This parse type parses the lines from 'min_connection_time.Parser' (which contains the code and minimum connection time
    for each airport) and adds them to slots in the corresponding airports HashMap
 */
public class AirportTimeParse extends CSVParse {

    private static final int AIRPORT_CODE = 0;
    private static final int MIN_CONNECTION_TIME = 1;

    @Override
    public void useCSVLine(String[] strLineArr) {
        //extract values from string array
        String code = strLineArr[AIRPORT_CODE];
        int minConnectionTime = Integer.parseInt(strLineArr[MIN_CONNECTION_TIME]);

        //update given airport object with minimum connection time
        Airport airport = AirportsDB.getInstance().getAirport(code);
        airport.setMinConnectionTime(minConnectionTime);
    }
}
