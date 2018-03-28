package Errors;

/**
 * @author Joshua Ehling
 * Exception thrown when user tries to redo command with empty stack
 */
public class EmptyRedoStackError extends Exception{
    public EmptyRedoStackError(){
        super("error, no commands to redo");
    }
}
