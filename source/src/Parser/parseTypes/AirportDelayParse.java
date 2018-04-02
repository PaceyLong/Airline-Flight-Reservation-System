package Parser.parseTypes;

import Airports.Airport;
import Airports.AirportsDB;

/**
  *  This parse type parses the lines from 'delay.Parser' (which contains the code and the delay of each airport)
  *  and adds them to slots in the corresponding airports HashMap
 */
public class AirportDelayParse extends CSVParse {

    private static final int AIRPORT_CODE = 0;
    private static final int DELAY = 1;

    @Override
    public void useCSVLine(String[] strLineArr) {
        //extract values from string array
        String code = strLineArr[AIRPORT_CODE];
        int delay = Integer.parseInt(strLineArr[DELAY]);

        //update given airport object with delay
        Airport airport = AirportsDB.getInstance().getAirport(code);
        airport.setDelayTime(delay);
    }
}
