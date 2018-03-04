package src.Commands;

import java.util.ArrayList;

/**
 * Top level abstract Command class for Command Pattern
 * Legend: {keyword literal}, [optional input], data
 * Receives parsed command string.
 * ASSUMPTION: valid input
 */
public interface Command {
    public void execute(ArrayList<String> input);
}
