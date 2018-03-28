package Commands;
import Reservations.Itinerary;

import Reservations.ReservationsDB;

import java.util.ArrayList;

/**
 * Command block that handles Delete Reservation Requests
 *
 * Legend: {keyword literal}, [optional input], data
 * Delete reservation request: {delete},passenger,origin,destination;
 *      passenger: name of the passenger who holds the reservation being deleted
 *      origin: three-letter code for the reservation's origin airport
 *      destination: three-letter code for the reservation's destination airport
 */
public class DeleteReservation extends UndoableCommand {
    /* Constants */
    private static final int DELETE_KEYWORD = 0;
    private static final int PASSENGER = 1;
    private static final int ORIGIN = 2;
    private static final int DESTINATION = 3;
    private ReservationsDB reservationsDB = ReservationsDB.getInstance();

    /* Attributes */
    private Itinerary deletedItinerary;

    public DeleteReservation(ArrayList<String> input){
        super(input);
    }

    @Override
    public void undo() {
        reservationsDB.reserveItinerary(deletedItinerary, input.get(PASSENGER));
    }

    @Override
    public void execute() {
        deletedItinerary = reservationsDB.getItinerary(input.get(PASSENGER), input.get(ORIGIN), input.get(DESTINATION));
        reservationsDB.deleteItinerary(input.get(PASSENGER), deletedItinerary);
    }
}
