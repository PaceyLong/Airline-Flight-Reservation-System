package Commands;

import java.util.ArrayList;

/**
 * Undoable Command - used to enable undoable functionality
 * Maintains state before command is applied for Undo/Redo
 */
public abstract class UndoableCommand extends Command {
    public abstract void undo();
}
