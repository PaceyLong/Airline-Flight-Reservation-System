package Commands;

import java.util.ArrayList;

/**
 * Command block that handles Delete Reservation Requests
 *
 * Legend: {keyword}, data
 * Delete reservation request: delete,passenger,origin,destination;
 *      passenger: name of the passenger who holds the reservation being deleted
 *      origin: three-letter code for the reservation's origin airport
 *      destination: three-letter code for the reservation's destination airport
 */
public class DeleteReservation implements Command {
    @Override
    public void execute(ArrayList<String> input) {
        // todo
    }
}
