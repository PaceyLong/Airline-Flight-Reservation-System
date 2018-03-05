package Commands;

import Airports.AirportsDB;
import Errors.*;
import Reservations.ReservationsDB;

import java.util.ArrayList;

/**
 * Parses input from user. Based on the start of the input determines which command to use to fulfill the customer's
 * request. Error checking in input is done here to ensure commands do not get invalid data.
 * @author Nick Asermily, nja9798@g.rit.edu
 */
public class InputParser {
    private AirportsDB airportsDB = AirportsDB.getInstance();
    private ReservationsDB reservationsDB = ReservationsDB.getInstance();
    private ArrayList<String> parsedInput = new ArrayList<>();
    private Command command;

    public InputParser(String input) throws Exception{
        parseInput(input);
    }

    /**
     * Parses the input by removing all whitespace and splitting by commas. Based on the first word in the input,
     * a command is chosen and errors are accounted for.
     * @param input: the String request from the user
     */
    public void parseInput(String input) throws Exception{
        input = input.replaceAll("\\s","");
        String[] split = input.split(",");
        for(String i : split){
            parsedInput.add(i);
        }

        switch(parsedInput.get(0)){
            case "info":
                try{
                    infoErrors();
                    this.setCommand(new FlightInfo());
                }catch(UnknownOriginException|UnknownDestinationException|InvalidConnectionLimitException|InvalidSortOrderException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "reserve":
                this.setCommand(new ReserveFlight());
                break;
            case "retrieve":
                try{
                    retrieveErrors();
                    this.setCommand(new RetrieveReservation());
                }catch(UnknownOriginException|UnknownDestinationException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "delete":
                this.setCommand(new DeleteReservation());
                break;
            case "airport":
                try{
                    airportErrors();
                    this.setCommand(new AirportInfo());
                }catch(UnknownAirportException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                throw new UnknownRequestException();
        }
    }

    /**
     * Sets the command of this request to the specified one.
     * @param command: Command to be changed to
     */
    public void setCommand(Command command){
        this.command = command;
    }

    /**
     * Tells the request to execute the functionality of its command.
     */
    public void executeRequest(){
        command.execute(parsedInput);
    }

    /**
     * Error checking if the request is looking for Flight Information
     * @throws Exception
     */
    public void infoErrors() throws Exception{
        String origin = parsedInput.get(1);
        String destination = parsedInput.get(2);
        String connections = null;
        String sortOrder = null;
        if(parsedInput.size() >= 4) {
            connections = parsedInput.get(3);
        }
        if(parsedInput.size() >= 5) {
            sortOrder = parsedInput.get(4);
        }

        if(airportsDB.getAirport(origin) == null){
            throw new UnknownOriginException();
        }else if(airportsDB.getAirport(destination) == null){
            throw new UnknownDestinationException();
        }else if(connections != null && !(connections.equals("0") || connections.equals("1") || connections.equals("2") || connections.equals(""))){
            throw new InvalidConnectionLimitException();
        }else if(sortOrder != null && !(sortOrder.equals("departure") || sortOrder.equals("arrival") || sortOrder.equals("airfare") || sortOrder.equals(""))){
            throw new InvalidSortOrderException();
        }
    }

    /**
     * Error checking if the request is looking to reserve a reservation
     * @throws Exception
     */
    public void reserveErrors(){
        String id = parsedInput.get(1);
        String passenger = parsedInput.get(2);
    }

    /**
     * Error checking if the request is looking for retrieving a reservation
     * @throws Exception
     */
    public void retrieveErrors() throws Exception{
        String passenger = parsedInput.get(1);
        String origin = parsedInput.get(2);
        String destination = parsedInput.get(3);

        if(airportsDB.getAirport(origin) == null){
            throw new UnknownOriginException();
        }else if(airportsDB.getAirport(destination) == null){
            throw new UnknownDestinationException();
        }
    }

    /**
     * Error checking if the request is looking to delete a reservation
     * @throws Exception
     */
    public void deleteErrors(){
        String passenger = parsedInput.get(1);
        String origin = parsedInput.get(2);
        String destination = parsedInput.get(3);
    }

    /**
     * Error checking if the request is looking for Airport Information
     ** @throws Exception
     */
    public void airportErrors() throws Exception{
        String airport = parsedInput.get(1);

        if(airportsDB.getAirport(airport) == null){
            throw new UnknownAirportException();
        }
    }
}
