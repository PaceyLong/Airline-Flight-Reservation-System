package Commands;

import Reservations.ReservationsDB;

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
    /* attributes */
    private static final int PASSENGER = 1;
    private static final int ORIGIN = 2;
    private static final int DESTINATION = 3;
    private static final int DEFAULT_SIZE = 2;
    private static final int PASSENGER_ORIGIN_SIZE = 3;
    private static final int PASSENGER_ORIGIN_DESTINATION_SIZE = 4;

    @Override
    public void execute(ArrayList<String> input) {
        // grab ReservationsDB singleton instance
        ReservationsDB reservationsDB = ReservationsDB.getInstance();
        if(input.size() == DEFAULT_SIZE){
            reservationsDB.retriveReservations(input.get(PASSENGER));
        } else if(input.size() == PASSENGER_ORIGIN_SIZE){
            reservationsDB.retrieveReservations(input.get(PASSENGER), input.get(ORIGIN));
        } else if(input.size() == PASSENGER_ORIGIN_DESTINATION_SIZE){
            reservationsDB.retrieveReservations(input.get(PASSENGER), input.get(ORIGIN), input.get(DESTINATION));
        } else{
            // todo throw error
        }
    }
}
