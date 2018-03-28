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
    private Stack<UndoableCommand> undoCommandStack;
    private Stack<UndoableCommand> redoCommandStack;

    /**
     * Constructor
     */
    public CommandManager(){
        undoCommandStack = new Stack<>();
        redoCommandStack = new Stack<>();
    }

    /**
     * Main execution method. Executes cmd argument
     * If the executed command is Undoable, add to Undo Stack
     * @param cmd Command object sent in
     */
    public void executeCommand(Command cmd){
        // execute command
        cmd.execute();
        // add to undo stack if UndoableCommand
        if(cmd instanceof UndoableCommand){
            // cast cmd to UndoableCommand and push to stack
            undoCommandStack.push((UndoableCommand) cmd);
        }
    }

    /**
     * Undo most recent Undoable Command
     * Add command to Redo Stack
     */
    public void undoCommand(){
        // verify undoStack isn't empty
        if(!undoCommandStack.empty()){
            UndoableCommand cmd = undoCommandStack.pop();
            cmd.undo();
            redoCommandStack.push(cmd);
        } else{
            System.out.println("There are no commands to undo");
        }
    }

    /**
     * Redo most recent undone Undoable Command
     * Add command to Undo Stack
     */
    public void redoCommand(){
        // verify redoStack isn't empty
        if(!redoCommandStack.empty()){
            UndoableCommand cmd = redoCommandStack.pop();
            cmd.execute();
            undoCommandStack.push(cmd);
        } else{
            System.out.println("There are no commands to redo");
        }
    }
}
