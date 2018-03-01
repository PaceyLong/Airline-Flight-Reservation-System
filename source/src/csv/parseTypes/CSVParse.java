package csv.parseTypes;
import org.json.simple.JSONArray;

import java.io.BufferedReader;
import java.io.FileReader;



public abstract class CSVParse {

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
    public abstract JSONArray useCSVLine(String[] strLineArr, JSONArray arr, int i);


}
