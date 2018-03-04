package src.Commands;

import java.util.ArrayList;

/**
 * Command block that handles Flight Info Request
 *
 * Legend: {keyword literal}, [optional input], data
 * Flight information request: {info},origin,destination[,connections[,sort-order]];
 *      origin: three-letter code for the origin airport
 *      destination: three-letter code for the destination airport
 *      connections: # of connections allowed in the returned itineraries. [0, 1, 2]
 *          Default connection limit: 2; Omitted fields are empty strings between commas
 *          Sort-order is one of: departure, arrival, airfare.
 *              Sorting will always be from lowest to highest by nature ordering for the field.
 *              The default sort order, if this field is omitted, is by time of departure.
 */
public class FlightInfo implements Command{
    @Override
    public void execute(ArrayList<String> input) {
        // todo
    }
}
