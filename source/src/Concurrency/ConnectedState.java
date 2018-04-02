package Concurrency;
import Commands.InputParser;

/**
 * Connected state for a user. Allows for a user to disconnect and input commands.
 * @author Nick Asermily, nja9798@g.rit.edu
 */
public class ConnectedState implements ClientState{
    String input = "";
    InputParser parser;

    /**
     * Disconnects the user and changes their state back to disconnected.
     * @param c: Client
     */
    @Override
    public void disconnect(Client c) {
        //Disconnects user
        input = "";
        c.setState(new DisconnectedState());
    }

    /**
     * A Connected user does not need to connect again. Does nothing.
     * @param c: Client
     */
    @Override
    public void connect(Client c) {
        //Already connected
    }

    /**
     * Parses inputted command from user and returns the resulting information.
     * @param c: Client
     * @param query: Input String
     * @return: Resulting information based on the input
     * @throws Exception: Exception thrown from InputParser from invalid input.
     */
    @Override
    public String inputQuery(Client c, String query) throws Exception{
        try{
            //output.setText(parser.executeRequest());
            parser = c.getInputParser();
            parser.parseInput(query);
            return parser.executeRequest();
        }catch(Exception e){
            throw e;
        }
    }
}
