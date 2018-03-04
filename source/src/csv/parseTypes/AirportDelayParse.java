package csv.parseTypes;

import Airports.Airport;

import java.util.HashMap;


/*
    This parse type parses the lines from 'delay.csv' (which contains the code and the delay of each airport)
    and adds them to slots in the corresponding airports HashMap
 */
public class AirportDelayParse extends CSVParse {

    @Override
    public HashMap useCSVLine(String[] strLineArr, HashMap hash) {
        //extract values from string array
        String code = strLineArr[0];
        int delay = Integer.parseInt(strLineArr[1]);

        //update given airport object with delay
        Airport airport = (Airport) hash.get(code);
        airport.setDelayTime(delay);
        hash.put(code, airport);
        return hash;
    }
}
