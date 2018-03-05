import Airports.AirportsDB;
import Reservations.ReservationsDB;
import TTARouteNetwork.FlightsDB;
import csv.CSVParser;
import Commands.InputParser;

import java.util.Scanner;


public class Main {
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

    public static void main(String[] args) throws Exception{

        CSVParser csvp = new CSVParser();
        csvp.createHashes();
        AirportsDB airports = csvp.getAirports();
        FlightsDB flights = csvp.getFlights();
        ReservationsDB reservations = csvp.getReservations();
        InputParser parser;
        Scanner scanner = new Scanner(System.in);
        String input = "";

        System.out.println("*************************************** Welcome to Airline Flight Reservation Server (AFRS)! *************************************");
        help();

        while(true){
            input += scanner.next();
            if(input.trim().contains("QUIT")){
                System.out.println("Thank you for using AFRS!");

                //save any reservations upon quitting
                csvp.writeToCSV();
                return;
            }
            if(input.trim().contains("HELP")){
                help();
                input = "";
                continue;
            }
            if(input.trim().endsWith(";")){
                try{
                    parser = new InputParser(input.substring(0, input.length() - 1));
                    parser.executeRequest();
                    input = "";
                }catch(Exception e){
                    input = "";
                }
            }
        }


    }

}
