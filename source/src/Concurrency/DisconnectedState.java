package Concurrency;

/**
 * @author Nick Asermily, nja9798@g.rit.edu
 */
public class DisconnectedState implements ClientState {


    @Override
    public void disconnect(Client c) {
        //Does nothing, already disconnected
    }

    @Override
    public void connect(Client c) {
        c.setState(new ConnectedState());
    }

    @Override
    public void inputQuery(Client c, String query){
        //Does nothing, not connected yet
    }
}
