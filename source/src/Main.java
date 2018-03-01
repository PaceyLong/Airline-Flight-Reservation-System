import csv.CSVParser;

import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException{

        //if files exist do nothing, else create the json files
        File fileAirports = new File("./src/Airports/airports.json");
        File fileFlights = new File("./src/TTARouteNetwork/flights.json");
        if(!fileAirports.exists() && !fileFlights.exists()){
            CSVParser csvp = new CSVParser();
            csvp.createJSON();
        }
    }

}
