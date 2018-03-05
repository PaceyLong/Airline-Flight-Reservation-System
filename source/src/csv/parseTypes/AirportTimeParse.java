package csv.parseTypes;
import Airports.Airport;
import Airports.AirportsDB;

import java.util.HashMap;

/*
    This parse type parses the lines from 'min_connection_time.csv' (which contains the code and minimum connection time
    for each airport) and adds them to slots in the corresponding airports HashMap
 */
public class AirportTimeParse extends CSVParse {

    @Override
    public void useCSVLine(String[] strLineArr) {
        //extract values from string array
        String code = strLineArr[0];
        int minConnectionTime = Integer.parseInt(strLineArr[1]);

        //update given airport object with minimum connection time
        Airport airport = AirportsDB.getInstance().getAirport(code);
        airport.setMinConnectionTime(minConnectionTime);
    }
}
