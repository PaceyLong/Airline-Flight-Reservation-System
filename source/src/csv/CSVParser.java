package csv;

import Airports.AirportsDB;
import TTARouteNetwork.FlightsDB;
import csv.parseTypes.*;


/*
this class is the "context" of the strategy pattern I have implemented here. This class is instantiated in the Main
and creates the HashMap objects for both flights and airports
*/
public class CSVParser {
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
        String csvPath = "./src/csv/";

        //read in all the airport data and adds to flights HashMap
        //check if files don't exist
        this.nameParse.parseCSV(csvPath + "airports.csv");
        this.delayParse.parseCSV(csvPath + "delay.csv");
        this.timeParse.parseCSV(csvPath + "min_connection_time.csv");
        this.weatherParse.parseCSV(csvPath + "weather.csv");


        //read in all the flight data and adds to flights HashMap
        this.flightParse.parseCSV(csvPath + "route_network.csv");


    }

    public AirportsDB getAirports() {
        return airports;
    }


    public FlightsDB getFlights() {
        return flights;
    }
}
