package Errors;

/**
 * @author Nick Asermily, nja9789@g.rit.edu
 * Exception thrown when the connection limit is not 0, 1, or 2
 */
public class InvalidConnectionLimitException extends Exception{
    public InvalidConnectionLimitException(){
        super("error,invalid connection limit");
    }
}
