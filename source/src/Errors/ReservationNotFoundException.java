package Errors;

/**
 * @author Joshua Ehling
 * Exception thrown when Reservation isn't found during a delete request
 * Thrown by: ReservationsDB.deleteReservation()
 */
public class ReservationNotFoundException extends Exception {
    public ReservationNotFoundException(){
        super("error, reservation not found");
    }
}
