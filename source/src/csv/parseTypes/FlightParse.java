package csv.parseTypes;
import TTARouteNetwork.Flight;

import java.util.ArrayList;
import java.util.HashMap;


/*
    This parse type parses the lines from 'route_network.csv' (which contains origin airport code, destination airport code,
    departure time, arrival time, flight number, and airfare for each flight) and adds them to slots in the
    corresponding flights HashMap
 */
public class FlightParse extends CSVParse {

    @Override
    public HashMap useCSVLine(String[] strLineArr, HashMap hash) {

        //extract data from string array
        String originAirport = strLineArr[0];
        String destinationAirport = strLineArr[1];
        String departureTime = strLineArr[2];
        String arrivalTime = strLineArr[3];
        int flightNumber = Integer.parseInt(strLineArr[4]);
        int airfare = Integer.parseInt(strLineArr[5]);

        //create new Flight object with defined values
        Flight flight = new Flight(originAirport, destinationAirport, departureTime, arrivalTime, flightNumber, airfare);
        String key = originAirport + " to " + destinationAirport;
        ArrayList<Flight> flights;


        if(hash.containsKey(key)){
            flights =  (ArrayList<Flight>) hash.get(key);
            flights.add(flight);
        }
        else{
            flights = new ArrayList<>();
            flights.add(flight);
        }
        hash.put(key, flights);
        return hash;

    }
}
