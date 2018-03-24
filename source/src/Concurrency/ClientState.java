package Concurrency;

/**
 * Interface for Connected States and Disconnected States.
 * @author Nick Asermily, nja9798@g.rit.edu
 */
public interface ClientState {
    public void connect(Client c);

    public void disconnect(Client c);

    public void inputQuery(Client c, String query);
}
