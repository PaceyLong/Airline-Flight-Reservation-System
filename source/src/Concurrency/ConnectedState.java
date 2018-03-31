package Concurrency;
import Commands.InputParser;

/**
 * @author Nick Asermily, nja9798@g.rit.edu
 */
public class ConnectedState implements ClientState{
    String input = "";
    InputParser parser;

    public ConnectedState(){
        System.out.println("************************************** Welcome to Airline Flight Reservation Server (AFRS)! **************************************");
        help();
    }

    @Override
    public void disconnect(Client c) {
        //Disconnects user
        input = "";
        c.setState(new DisconnectedState());
    }

    @Override
    public void connect(Client c) {
        //Already connected
    }

    @Override
    public String inputQuery(Client c, String query) throws Exception{
        if(input.trim().endsWith(";")){
            try{
                //output.setText(parser.executeRequest());
                parser = new InputParser();
                return parser.executeRequest();
            }catch(Exception e){
                throw e;
            }
        }
    }

    public static void help(){
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Input should follow one of the following formats (Anything in brackets are optional and all commands are ended with semi-colons): ");
        System.out.println("Flight information request: info,origin,destination[,connections[,sort-order]];");
        System.out.println("Make reservation request: reserve,id,passenger;");
        System.out.println("Retrieve reservations request: retrieve,passenger[,origin[,destination]];");
        System.out.println("Delete reservation request: delete,passenger,origin,destination;");
        System.out.println("Airport information request: airport,airport;");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Please input a command (Type 'HELP' to see commands again, Type 'QUIT' to exit): ");
    }


}
