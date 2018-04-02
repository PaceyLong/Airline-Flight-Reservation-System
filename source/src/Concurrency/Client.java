package Concurrency;

import Commands.InputParser;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Client class for a single user. A user transitions between a connected state
 * or a disconnected state where no inputs will be handled.
 * Each user has a uniqueID to distinguish them from each other.
 * @author Nick Asermily, nja9798@g.rit.edu
 */
public class Client {
    private int id;
    private InputParser inputParser;
    private ClientState state;

    public Client(int id){
        this.state = new DisconnectedState();
        this.id = id;
    }

    /**
     * User follows a certain disconnect functionality based on their current state.
     */
    public void disconnect(){
        this.state.disconnect(this);
    }

    /**
     * User follows a certain connect functionality based on their current state.
     */
    public void connect(){
        this.state.connect(this);
    }

    /**
     * User follows a certain functionality for their inputted string based on their current state.
     * @param s: String input
     * @return Resulting String from command
     * @throws Exception: If any error is found while parsing their input. See InputParser for specifics.
     */
    public String inputQuery(String s) throws Exception{
        try {
            return this.state.inputQuery(this, s);
        }catch(Exception e){
            throw e;
        }
    }

    /**
     * Setter so the client has it's own rendition of the InputParser
     * @param parser
     */
    public void setInputParser(InputParser parser){
        this.inputParser = parser;
    }

    /**
     * Accessor for client's personal InputParser
     * @return inputParser
     */
    public InputParser getInputParser(){
        return this.inputParser;
    }

    /**
     * Changes the client's state
     * @param state: State to be changed to
     */
    public void setState(ClientState state){
        this.state = state;
    }

    /**
     * Gets the user's ID
     * @return User ID
     */
    public int getId(){
        return id;
    }

    /**
     * Changes the user ID
     * @param id: User id to be set to
     */
    public void setId(int id){
        this.id = id;
    }
}
