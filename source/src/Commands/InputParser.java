package Commands;

import Airports.AirportsDB;
import Reservations.Itinerary;
import Reservations.ReservationsDB;

import java.util.ArrayList;

/**
 * Parses input from user. Based on the start of the input determines which command to use to fulfill the customer's
 * request. Error checking in input is done here to ensure commands do not get invalid data.
 * @author Nick Asermily, nja9798@g.rit.edu
 */
public class InputParser {
    private AirportsDB airportsDB;
    private ReservationsDB reservationsDB;
    private ArrayList<String> parsedInput;
    private Command command;

    public InputParser(String input){
        parseInput(input);
    }

    /**
     * Parses the input by removing all whitespace and splitting by commas. Based on the first word in the input,
     * a command is chosen and errors are accounted for.
     * @param input: the String request from the user
     */
    public void parseInput(String input){
        input = input.replaceAll("\\s","");
        String[] split = input.split(",");
        for(String i : split){
            parsedInput.add(i);
        }

        switch(parsedInput.get(0)){
            case "info":
                this.setCommand(new FlightInfo());
                break;
            case "reserve":
                this.setCommand(new ReserveFlight());
                break;
            case "retrieve":
                this.setCommand(new RetrieveReservation());
                break;
            case "delete":
                this.setCommand(new DeleteReservation());
                break;
            case "airport":
                this.setCommand(new AirportInfo());
                break;
            default:
                System.out.println("error,unknown request");
                break;
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
     * @return true if errors are found, false if no errors
     */
    public boolean infoErrors(){
        Boolean errors = true;
        String origin = parsedInput.get(1);
        String destination = parsedInput.get(2);
        String connections = parsedInput.get(3);
        String sortOrder = parsedInput.get(4);

        if(airportsDB.getAirport(origin) == null){
            System.out.println("error,unknown origin");
        }else if(airportsDB.getAirport(destination) == null){
            System.out.println("error,unknown destination");
        }else if(connections != null && !(connections.equals("0") || connections.equals("1") || connections.equals("2"))){
            System.out.println("error,invalid connection limit");
        }else if(sortOrder != null && !(sortOrder.equals("departure") || sortOrder.equals("arrival") || sortOrder.equals("airfare"))){
            System.out.println("error,invalid sort order");
        }else{
            errors = false;
        }

        return errors;
    }

    /**
     * Error checking if the request is looking for retrieving a reservation
     * @return true if errors are found, false if no errors
     */
    public boolean retrieveErrors(){
        Boolean errors = true;
        String passenger = parsedInput.get(1);
        String origin = parsedInput.get(2);
        String destination = parsedInput.get(3);

        if(airportsDB.getAirport(origin) == null){
            System.out.println("error,unknown origin");
        }else if(airportsDB.getAirport(destination) == null){
            System.out.println("error,unknown destination");
        }else{
            errors = false;
        }

        return errors;
    }

    /**
     * Error checking if the request is looking to delete a reservation
     * @return true if errors are found, false if no errors
     */
    public boolean deleteErrors(){
        Boolean errors = true;
        String passenger = parsedInput.get(1);
        String origin = parsedInput.get(2);
        String destination = parsedInput.get(3);

        return true;
    }

    /**
     * Error checking if the request is looking for Airport Information
     * @return true if errors are found, false if no errors
     */
    public boolean airportErrors(){
        Boolean errors = true;
        String airport = parsedInput.get(1);

        if(airportsDB.getAirport(airport) == null){
            System.out.println("error,unknown airport");
        }else{
            errors = false;
        }

        return errors;
    }
}
