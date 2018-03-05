package Commands;

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
    private String origin;
    private static final int ORIGIN_INDEX = 1;
    private String destination;
    private static final int DESTINATION_INDEX = 2;
    private String connections;
    private static final int CONNECTIONS_INDEX = 3;
    private String sortOrder;
    private static final int SORT_ORDER_INDEX = 4;
    private static final String DEFAULT_CONNECTION_LIMIT = "2";
    private static final String DEFAULT_SORT_ORDER = "departure";

    @Override
    public void execute(ArrayList<String> input) {
        origin = input.get(ORIGIN_INDEX);
        destination = input.get(DESTINATION_INDEX);
        if(input.size() >= 4) {
            connections = input.get(CONNECTIONS_INDEX);
        }else{
            connections = DEFAULT_CONNECTION_LIMIT;
        }
        if(input.size() >= 5) {
            sortOrder = input.get(SORT_ORDER_INDEX);
        }else{
            sortOrder = DEFAULT_SORT_ORDER;
        }


    }

}
