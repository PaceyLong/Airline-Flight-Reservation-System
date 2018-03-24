package Concurrency;

import Commands.InputParser;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Nick Asermily, nja9798@g.rit.edu
 */
public class Client {
    private AtomicInteger id;
    private InputParser inputParser;
    private ClientState state;

    public Client(AtomicInteger id){
        this.state = new DisconnectedState();
        this.id = id;
    }

    public void disconnect(){
        this.state.disconnect(this);
    }

    public void connect(){
        this.state.connect(this);
    }

    public void inputQuery(String s){
        this.state.inputQuery(this, s);
    }

    public void setState(ClientState state){
        this.state = state;
    }

    public AtomicInteger getId(){
        return id;
    }

    public void setId(AtomicInteger id){
        this.id = id;
    }
}
