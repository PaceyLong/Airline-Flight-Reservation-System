package Commands;

import java.util.ArrayList;

/**
 * Command block that handles Retrieve Reservation Requests
 *
 * Legend: {keyword literal}, [optional input], data
 * Retrieve reservations request: {retrieve},passenger[,origin[,destination]];
 *      passenger: name of the passenger whose reservations are being retrieved
 *      origin: three-letter code for the origin airport that will filter the retrieved reservations.
 *          If the request has a destination filter but no origin filter, the origin field will be empty
 *      destination: three-letter code for the destination airport that will filter the retrieved reservations
 */
public class RetrieveReservation implements Command{
    @Override
    public void execute(ArrayList<String> input) {
        // todo
    }
}
