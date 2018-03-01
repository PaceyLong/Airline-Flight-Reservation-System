package csv;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVParser {


    public CSVParser(){

    }

    public void parseCSV(String csvPath, JSONArray arr){
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvPath));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] strArray = line.split(",");
                JSONObject obj = new JSONObject();
                obj.put("code", strArray[0]);
                obj.put("name", strArray[1]);
                arr.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void createJSON(){

        createAirportsJSON();
        createFlightsJSON();
    }


    public void createAirportsJSON(){
        JSONArray airportsArr = new JSONArray();
        parseCSV("./src/csv/airports.csv", airportsArr);
        writeJSONToFile("./src/Airports/airports.json", airportsArr);
    }

    public void createFlightsJSON(){
        JSONObject flightsObj = new JSONObject();
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
