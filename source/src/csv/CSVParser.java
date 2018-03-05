package csv;

import Airports.AirportsDB;
import TTARouteNetwork.FlightsDB;
import csv.parseTypes.*;


/**
*   this class is the "context" of the strategy pattern I have implemented here. This class is instantiated in the Main
*   and creates the HashMap objects for both flights and airports
*/
public class CSVParser {
    public static String AIRPORTS_NAME_PATH =  "./src/CSVFiles/airports.csv";
    public static String AIRPORTS_DELAY_PATH = "./src/CSVFiles/delay.csv";
    public static String AIRPORTS_TIME_PATH = "./src/CSVFiles/min_connection_time.csv";
    public static String AIRPORTS_WEATHER_PATH = "./src/CSVFiles/weather.csv";
    public static String FLIGHTS_PATH = "./src/CSVFiles/route_network.csv";

    private CSVParse nameParse;
    private CSVParse delayParse;
    private CSVParse timeParse;
    private CSVParse weatherParse;
    private CSVParse flightParse;
    private AirportsDB airports;
    private FlightsDB flights;


    public CSVParser(){
        // all the parse types the parser will use to generate the HashMaps
        this.nameParse = new AirportNameParse();
        this.delayParse = new AirportDelayParse();
        this.timeParse = new AirportTimeParse();
        this.weatherParse = new AirportWeatherParse();
        this.flightParse = new FlightParse();

        //airports and flights HashMaps will be filled with their data in the createHashes function call
        this.airports = AirportsDB.getInstance();
        this.flights = FlightsDB.getInstance();
    }

    public void createHashes(){

        //read in all the airport data and adds to flights HashMap
        //check if files don't exist
        this.nameParse.parseCSV(AIRPORTS_NAME_PATH);
        this.delayParse.parseCSV(AIRPORTS_DELAY_PATH);
        this.timeParse.parseCSV(AIRPORTS_TIME_PATH);
        this.weatherParse.parseCSV(AIRPORTS_WEATHER_PATH);


        //read in all the flight data and adds to flights HashMap
        this.flightParse.parseCSV(FLIGHTS_PATH);
    }

    public AirportsDB getAirports() {
        return airports;
    }


    public FlightsDB getFlights() {
        return flights;
    }
}
