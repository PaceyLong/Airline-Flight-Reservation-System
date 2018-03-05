package csv.parseTypes;

import Airports.Airport;
import Airports.AirportsDB;

import java.util.HashMap;


/*
    This parse type parses the lines from 'delay.csv' (which contains the code and the delay of each airport)
    and adds them to slots in the corresponding airports HashMap
 */
public class AirportDelayParse extends CSVParse {

    @Override
    public void useCSVLine(String[] strLineArr) {
        //extract values from string array
        String code = strLineArr[0];
        int delay = Integer.parseInt(strLineArr[1]);

        //update given airport object with delay
        Airport airport = AirportsDB.getInstance().getAirport(code);
        airport.setDelayTime(delay);
    }
}
