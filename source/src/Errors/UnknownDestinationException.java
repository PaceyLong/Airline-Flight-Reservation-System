package Errors;

/**
 * @author Nick Asermily, nja9789@g.rit.edu
 * Exception thrown when the destination airport is unknown
 */
public class UnknownDestinationException extends Exception {
    public UnknownDestinationException(){
        super("error,unknown destination");
    }
}
