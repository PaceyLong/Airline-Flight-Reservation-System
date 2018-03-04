package Errors;

/**
 * @author Nick Asermily, nja9789@g.rit.edu
 * Exception thrown when the origin airport is unknown
 */
public class UnknownOriginException extends Exception {
    public UnknownOriginException(){
        super("error,unknown origin");
    }
}
