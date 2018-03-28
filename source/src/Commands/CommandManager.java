package Commands;

import java.util.Stack;

/**
 * @author Joshua Ehling
 *
 * Manages Command objects. Enables Undo/Redo functionality.
 * Tracks local stack of undo/redo commands as they are executed
 * Executes passed in command objects.
 */
public class CommandManager {
    /* attributes */
    private Stack<Command> undoCommandStack;
    private Stack<Command> redoCommandStack;

    /**
     * Constructor
     */
    public CommandManager(){
        undoCommandStack = new Stack<>();
        redoCommandStack = new Stack<>();
    }

    public void executeCommand(Command cmd){
        // TODO
    }

    public void undoCommand(){
        // TODO
    }

    public void redoCommand(){
        // TODO
    }
}
