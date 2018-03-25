package Commands;

import java.util.ArrayList;

/**
 * Undoable Command - used to enable undoable functionality
 * Maintains state before command is applied for Undo/Redo
 */
public interface UndoableCommand extends Command {
    public void undo();
}
