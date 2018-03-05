package Errors;

/**
 * @author Nick Asermily, nja9789@g.rit.edu
 * Exception thrown when the server receives an unknown request type
 */
public class UnknownRequestException extends Exception {
    public UnknownRequestException(){
        super("error,unknown request");
    }
}
