package csv;

import Airports.AirportsDB;
import TTARouteNetwork.Flight;
import csv.parseTypes.*;

import java.util.ArrayList;
import java.util.HashMap;


/*
this class is the "context" of the strategy pattern i have implemented here. This class is instantiated in the Main
and creates the HashMap objects for both flights and airports
*/
public class CSVParser {
    private CSVParse nameParse;
    private CSVParse delayParse;
    private CSVParse timeParse;
    private CSVParse weatherParse;
    private CSVParse flightParse;
    private AirportsDB airports;
    private HashMap<String, ArrayList<Flight>> flights;


    public CSVParser(){
        // all the parse types the parser will use to generate the HashMaps
        this.nameParse = new AirportNameParse();
        this.delayParse = new AirportDelayParse();
        this.timeParse = new AirportTimeParse();
        this.weatherParse = new AirportWeatherParse();
        this.flightParse = new FlightParse();

        //airports and flights HashMaps will be filled with their data in the createHashes function call
        this.airports = AirportsDB.getInstance();
        this.flights = new HashMap<>();
    }

    public void createHashes(){
        String csvPath = "./src/csv/";

        //read in all the airport data and adds to flights HashMap
        this.airports.setAirportsDBHashMap(this.nameParse.parseCSV(csvPath+"airports.csv", this.airports.getAirportsDBHashMap()));
        this.airports.setAirportsDBHashMap(this.delayParse.parseCSV(csvPath+"delay.csv", this.airports.getAirportsDBHashMap()));
        this.airports.setAirportsDBHashMap(this.timeParse.parseCSV(csvPath+"min_connection_time.csv", this.airports.getAirportsDBHashMap()));
        this.airports.setAirportsDBHashMap(this.weatherParse.parseCSV(csvPath+"weather.csv", this.airports.getAirportsDBHashMap()));


        //read in all the flight data and adds to flights HashMap
        //this.flights = this.flightParse.parseCSV(csvPath+"route_network.csv", this.flights);

    }

    public AirportsDB getAirports() {
        return airports;
    }

    public HashMap<String, ArrayList<Flight>> getFlights() {
        return flights;
    }
}
