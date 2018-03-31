package Concurrency;

import Commands.InputParser;

import java.util.concurrent.atomic.AtomicInteger;

/**
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

    public void disconnect(){
        this.state.disconnect(this);
    }

    public void connect(){
        this.state.connect(this);
    }

    public String inputQuery(String s){
        return this.state.inputQuery(this, s);
    }

    public void setState(ClientState state){
        this.state = state;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}
