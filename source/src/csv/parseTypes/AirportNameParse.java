package csv.parseTypes;
import Airports.Airport;
import Airports.AirportsDB;

import java.util.ArrayList;
import java.util.HashMap;

/*
    This parse type parses the lines from 'airports.csv' (which contains the name and the code of each airport)
    and adds them to slots in the corresponding airports HashMap
 */
public class AirportNameParse extends CSVParse {

    @Override
    public void useCSVLine(String[] strLineArr) {
        //extract values from string array
        String name = strLineArr[1];
        String code = strLineArr[0];

        //create new airport object, add to airport hash
        Airport airport = new Airport(name, code, new ArrayList<>(), 0, 0);
        AirportsDB.getInstance().addAirport(code, airport);
    }
}
