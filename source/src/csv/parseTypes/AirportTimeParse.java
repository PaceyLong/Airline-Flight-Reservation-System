package csv.parseTypes;
import Airports.Airport;

import java.util.HashMap;

/*
    This parse type parses the lines from 'min_connection_time.csv' (which contains the code and minimum connection time
    for each airport) and adds them to slots in the corresponding airports HashMap
 */
public class AirportTimeParse extends CSVParse {

    @Override
    public HashMap useCSVLine(String[] strLineArr, HashMap hash) {
        //extract values from string array
        String code = strLineArr[0];
        int minConnectionTime = Integer.parseInt(strLineArr[1]);

        //update given airport object with minimum connection time
        Airport airport = (Airport) hash.get(code);
        airport.setMinConnectionTime(minConnectionTime);
        hash.put(code, airport);
        return hash;
    }
}
