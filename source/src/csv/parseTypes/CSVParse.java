package csv.parseTypes;
import org.json.simple.JSONArray;

import java.io.BufferedReader;
import java.io.FileReader;


/*
    The abstract "Strategy" class from the strategy pattern. Each type of parse inherits from this class by
    implementing useCSVLine.
 */
public abstract class CSVParse {

    //runs through the csv lines and adds json data to objects within the flights or airports json array
    public JSONArray parseCSV(String path, JSONArray arr){
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] strLineArr = line.split(",");
                arr = useCSVLine(strLineArr, arr, i);
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    /*
    creates, adds, and modifies json objects for the given JSONArray arr (this is either flights or airports). Each parse type
    has different data to add to each json object so it has been made abstract and is implemented by each of the child parse
    types. This function returns a new modified instance of the json array it was passed.
    */
    public abstract JSONArray useCSVLine(String[] strLineArr, JSONArray arr, int i);


}
