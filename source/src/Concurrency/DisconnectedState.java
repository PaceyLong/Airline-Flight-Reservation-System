package Concurrency;

/**
 * User disconnected state. This state should prevent any inputs that aren't for connecting to the AFRS.
 * @author Nick Asermily, nja9798@g.rit.edu
 */
public class DisconnectedState implements ClientState {

    /**
     * Disconnecting a disconnected user should do nothing.
     * @param c: Client
     */
    @Override
    public void disconnect(Client c) {
        //Does nothing, already disconnected
    }

    /**
     * Connecting a client changes their state so inputs can be handled.
     * @param c: Client
     */
    @Override
    public void connect(Client c) {
        c.setState(new ConnectedState());
    }

    /**
     * Inputs will not be handled by a disconnected user
     * @param c: Client
     * @param query: String input
     * @return: A soft error message telling the user to connect.
     */
    @Override
    public String inputQuery(Client c, String query){
        //Does nothing, not connected yet
        String s = "You are not connected. Please connect by typing 'CONNECT'.";
        return s;
    }
}
