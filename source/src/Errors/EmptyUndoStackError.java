package Errors;

/**
 * @author Joshua Ehling
 * Exception thrown when user tries to undo a command when stack is empty
 */
public class EmptyUndoStackError extends Exception{
    public EmptyUndoStackError(){
        super("error, no commands to undo");
    }
}
