package Errors;

/**
 * @author Joshua Ehling
 * Exception thrown when a Passenger tries to reserve a non-unique Itinerary (origin, destination)
 * Thrown by: Reservations.ReservationsDB
 */
public class DuplicateReservationException extends Exception{
    public DuplicateReservationException(){
        super("error, duplicate reservation");
    }
}
