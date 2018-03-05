package Commands;

import java.util.ArrayList;

/**
 * Command block that handles Reserve Flight Request
 *
 * Legend: {keyword literal}, [optional input], data
 * Make reservation request: {reserve},id,passenger;
 *      id: identifier in the last flight information request for the itinerary being reserved.
 *          The position number, starting with 1 (0), of the itinerary in the last AFRS response to a Flight Info Query
 *      passenger: name of the passenger making the reservation
 */
public class ReserveFlight implements Command{
    /* attributes */
    private static final int ID = 1;
    private static final int PASSENGER = 2;

    @Override
    public void execute(ArrayList<String> input) {
        // TODO
    }
}
