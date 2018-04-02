package Commands;

import java.util.ArrayList;

/**
 * Top level abstract Command class for Command Pattern
 * Legend: {keyword literal}, [optional input], data
 * Receives parsed command string.
 * ASSUMPTION: valid input
 */
public abstract class Command {
    /* Attributes */
    protected ArrayList<String> input;

    /**
     * Constructor
     * @param input - user input
     */
    public Command(ArrayList<String> input){
        this.input = input;
    }

    /**
     * Universal execution method
     * Throws specified exception if error condition is met
     */
    public abstract String execute() throws Exception;
}
