package Errors;

/**
 * @author Nick Asermily, nja9789@g.rit.edu
 * Exception thrown when the airport is unknown
 */
public class UnknownAirportException extends Exception {
    public UnknownAirportException(){
        super("error,unknown airport");
    }
}
