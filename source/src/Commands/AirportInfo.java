package Commands;

import Airports.Airport;
import java.util.ArrayList;

/**
 * Command block that handles logic required for Airport Info Request
 *
 * Legend: {keyword literal}, [optional input], data
 * Airport information request: {airport},airport;
 *     airport is the three-letter code for the airport whose information is requested
 *
 * Airport information request: airport,airport-name,weather,temperature,delay
 *      airport-name is the name of the airport whose information was requested
 *      weather is the current weather
 *      temperature is the current temperature
 *      delay is the current delay time in minutes
 * Example response: airport,Orlando,sunny,95,15
 * If the airport is unknown: error,unknown airport
 */
public class AirportInfo implements Command{
    /* Index location for "airport" keyword */
    private static final int AIRPORT_KEYWORD = 0;
    /* Index location for airport code */
    private static final int AIRPORT_CODE = 1;

    @Override
    public void execute(ArrayList<String> input) {
        /* Grab Airport from AirportsDB*/
        Airport airport = AirportsDB.get(input.get(AIRPORT_CODE));
        System.out.println(input.get(AIRPORT_KEYWORD) + "," + airport.toString());
    }
}
