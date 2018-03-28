package Errors;

/**
 * @author Joshua Ehling
 * Exception thrown to confirm that Redo has been executed successfully
 * Allows InputParser to halt execution
 */
public class RedoCommandFlag extends Exception{
    public RedoCommandFlag(){
        super("command redone");
    }
}
