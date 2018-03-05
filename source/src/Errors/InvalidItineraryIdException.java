package Errors;

/**
 * @author Nick Asermily, nja9789@g.rit.edu
 * Exception thrown when the itinerary id does not match a current itinerary
 */
public class InvalidItineraryIdException extends Exception {
    public InvalidItineraryIdException(){
        super("error,invalid id");
    }
}
