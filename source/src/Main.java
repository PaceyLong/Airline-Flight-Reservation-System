import csv.CSVParser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
        //if json objects not made yet
        CSVParser csvp = new CSVParser();
        csvp.createJSON();
        //else
        //do nothing


    }

}
