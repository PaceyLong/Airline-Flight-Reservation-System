package Errors;

/**
 * @author Nick Asermily, nja9789@g.rit.edu
 * Exception thrown when the sort order is invalid
 */
public class InvalidSortOrderException extends Exception {
    public InvalidSortOrderException(){
        super("error,invalid sort order");
    }
}
