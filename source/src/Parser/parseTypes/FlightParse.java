package Parser.parseTypes;
import TTARouteNetwork.Flight;
import TTARouteNetwork.FlightsDB;


/**
 *   This parse type parses the lines from 'route_network.Parser' (which contains origin airport code, destination airport code,
 *   departure time, arrival time, flight number, and airfare for each flight) and adds them to slots in the
 *   corresponding flights HashMap
 */
public class FlightParse extends CSVParse {

    private static final int ORIGIN_AIRPORT = 0;
    private static final int DESTINATION_AIRPORT = 1;
    private static final int DEPARTURE_TIME = 2;
    private static final int ARRIVAL_TIME = 3;
    private static final int FLIGHT_NUMBER = 4;
    private static final int AIRFARE = 5;


    @Override
    public void useCSVLine(String[] strLineArr) {

        //extract data from string array
        String originAirport = strLineArr[ORIGIN_AIRPORT];
        String destinationAirport = strLineArr[DESTINATION_AIRPORT];
        String departureTime = strLineArr[DEPARTURE_TIME];
        String arrivalTime = strLineArr[ARRIVAL_TIME];
        int flightNumber = Integer.parseInt(strLineArr[FLIGHT_NUMBER]);
        int airfare = Integer.parseInt(strLineArr[AIRFARE]);

        //create new Flight object with defined values
        Flight flight = new Flight(originAirport, destinationAirport, departureTime, arrivalTime, flightNumber, airfare);
        FlightsDB.getInstance().addFlight(flightNumber, flight);

    }
}
