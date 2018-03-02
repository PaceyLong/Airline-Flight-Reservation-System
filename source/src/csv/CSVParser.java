package csv;

import csv.parseTypes.*;
import org.json.simple.JSONArray;

import java.io.FileWriter;
import java.io.IOException;


/*
this class is the "context" of the strategy pattern i have implemented here. This class is created in the Main
and writes json objects to both flights.json and airports.json.
*/
public class CSVParser {
    private CSVParse nameParse;
    private CSVParse delayParse;
    private CSVParse timeParse;
    private CSVParse weatherParse;
    private CSVParse flightParse;
    private JSONArray airports;
    private JSONArray flights;


    public CSVParser(){
        // all the parse types the parser will use to generate the json objects
        this.nameParse = new AirportNameParse();
        this.delayParse = new AirportDelayParse();
        this.timeParse = new AirportTimeParse();
        this.weatherParse = new AirportWeatherParse();
        this.flightParse = new FlightParse();
        //airports and flights json arrays will be filled with their data in the createJSON function call
        this.airports = new JSONArray();
        this.flights = new JSONArray();
    }

    public void createJSON(){
        String csvPath = "./src/csv/";

        //read in all the airport data and write to json file
        this.airports = this.nameParse.parseCSV(csvPath+"airports.csv", this.airports);
        this.airports = this.delayParse.parseCSV(csvPath+"delay.csv", this.airports);
        this.airports = this.timeParse.parseCSV(csvPath+"min_connection_time.csv", this.airports);
        this.airports = this.weatherParse.parseCSV(csvPath+"weather.csv", this.airports);
        writeJSONToFile("./src/Airports/airports.json", this.airports);

        //read in all the flight data and write to json file
        this.flights = this.flightParse.parseCSV(csvPath+"route_network.csv", this.flights);
        writeJSONToFile("./src/TTARouteNetwork/flights.json", this.flights);

    }

    public void writeJSONToFile(String path, JSONArray obj){

        try (FileWriter file = new FileWriter(path)) {
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to " + path);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}