package Parser;

import Airports.AirportsDB;
import Parser.parseTypes.*;
import Reservations.Itinerary;
import Reservations.Reservable;
import Reservations.ReservationsDB;
import TTARouteNetwork.FlightsDB;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/**
*   this class is the "context" of the strategy pattern I have implemented here. This class is instantiated in the Main
*   and creates the HashMap objects for both flights and airports
 *   Author: Ethan Della Posta
*/
public class CSVParser {
    private static String AIRPORTS_NAME_PATH =  "./source/src/CSVFiles/airports.csv";
    private static String AIRPORTS_DELAY_PATH = "./source/src/CSVFiles/delay.csv";
    private static String AIRPORTS_TIME_PATH = "./source/src/CSVFiles/min_connection_time.csv";
    private static String AIRPORTS_WEATHER_PATH = "./source/src/CSVFiles/weather.csv";
    private static String FLIGHTS_PATH = "./source/src/CSVFiles/route_network.csv";
    private static String RESERVATIONS_PATH = "./source/src/CSVFiles/reservations.csv";

    private CSVParse nameParse;
    private CSVParse delayParse;
    private CSVParse timeParse;
    private CSVParse weatherParse;
    private CSVParse flightParse;
    private CSVParse reservationParse;
    private AirportsDB airports;
    private FlightsDB flights;
    private ReservationsDB reservations;


    /**
     * Defines each parser as a new CSVParse type, and each database singleton object
     */
    public CSVParser(){
        // all the parse types the parser will use to generate the HashMaps
        this.nameParse = new AirportNameParse();
        this.delayParse = new AirportDelayParse();
        this.timeParse = new AirportTimeParse();
        this.weatherParse = new AirportWeatherParse();
        this.flightParse = new FlightParse();
        this.reservationParse = new ReservationParse();

        //airports and flights HashMaps will be filled with their data in the createHashes function call
        this.airports = AirportsDB.getInstance();
        this.flights = FlightsDB.getInstance();
        this.reservations = ReservationsDB.getInstance();
    }

    /**
     * Creates the hashes for each database singleton object by using the various parse types
     */
    public void createHashes(){

        //read in all the airport data and adds to flights HashMap
        //check if files don't exist
        this.nameParse.parseCSV(AIRPORTS_NAME_PATH);
        this.delayParse.parseCSV(AIRPORTS_DELAY_PATH);
        this.timeParse.parseCSV(AIRPORTS_TIME_PATH);
        this.weatherParse.parseCSV(AIRPORTS_WEATHER_PATH);


        //read in all the flight data and adds to flights HashMap
        this.flightParse.parseCSV(FLIGHTS_PATH);

        //read in all the reservation data and adds to reservation HashMap
        this.reservationParse.parseCSV(RESERVATIONS_PATH);
    }

    /**
     * Accessor method for airports
     * @return airports
     */
    public AirportsDB getAirports() {
        return airports;
    }


    /**
     * Accessor method for flights
     * @return flights
     */
    public FlightsDB getFlights() {
        return flights;
    }

    /**
     * Accessor method for reservations
     * @return reservations
     */
    public ReservationsDB getReservations() {
        return reservations;
    }

    /**
     * Singular Purpose
     * Writes reservations to reservations.Parser in the following format:
     * Passenger Name, Airfare, Flight Number, Origin Airport, Departure Time, Destination Airport, Arrival Time, ....
     */
    public void writeToCSV(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(RESERVATIONS_PATH));
            for (String passengerName : this.reservations.parserGetReservationKeySet()) {
                for (Itinerary itinerary : this.reservations.parserGetReservations( passengerName)) {
                    writer.write(passengerName + ",");
                    for(Reservable flightReservable : itinerary.getReservables()){
                        writer.write(flightReservable.toString() + ",");
                    }
                    writer.write("\n");
                }
            }
            writer.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
