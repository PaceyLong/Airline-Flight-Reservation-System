package Errors;

/**
 * @author Joshua Ehling
 * Exception thrown to confirm that Undo has been executed successfully
 * Allows InputParser to halt execution
 */
public class UndoCommandFlag extends Exception{
    public UndoCommandFlag(){
        super("command undone");
    }
}
