package csv.parseTypes;
import TTARouteNetwork.Flight;
import TTARouteNetwork.FlightsDB;

import java.util.HashMap;


/*
    This parse type parses the lines from 'route_network.csv' (which contains origin airport code, destination airport code,
    departure time, arrival time, flight number, and airfare for each flight) and adds them to slots in the
    corresponding flights HashMap
 */
public class FlightParse extends CSVParse {

    @Override
    public void useCSVLine(String[] strLineArr) {

        //extract data from string array
        String originAirport = strLineArr[0];
        String destinationAirport = strLineArr[1];
        String departureTime = strLineArr[2];
        String arrivalTime = strLineArr[3];
        int flightNumber = Integer.parseInt(strLineArr[4]);
        int airfare = Integer.parseInt(strLineArr[5]);

        //create new Flight object with defined values
        Flight flight = new Flight(originAirport, destinationAirport, departureTime, arrivalTime, flightNumber, airfare);
        FlightsDB.getInstance().addFlight(flightNumber, flight);

    }
}
