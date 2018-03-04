import Airports.AirportsDB;
import csv.CSVParser;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException{

        CSVParser csvp = new CSVParser();
        csvp.createHashes();
        AirportsDB airports = csvp.getAirports();
        //HashMap airports = csvp.getAirports();


        // Create databases for session
        //AirportsDB airportsDB = AirportsDB.getInstance(); // TODO implement generation

    }

}
