import Airports.AirportsDB;
import TTARouteNetwork.FlightsDB;
import csv.CSVParser;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException{

        CSVParser csvp = new CSVParser();
        csvp.createHashes();
        AirportsDB airports = csvp.getAirports();
        FlightsDB flights = csvp.getFlights();



    }

}
