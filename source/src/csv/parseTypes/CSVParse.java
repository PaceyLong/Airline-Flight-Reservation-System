package csv.parseTypes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;


/*
    The abstract "Strategy" class from the strategy pattern. Each type of parse inherits from this class by
    implementing useCSVLine.
 */
public abstract class CSVParse {

    //runs through the csv lines and adds json data to objects within the flights or airports json array
    public HashMap parseCSV(String path, HashMap hash){
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] strLineArr = line.split(",");
                hash = useCSVLine(strLineArr, hash);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }

    /*
    creates, adds, and modifies objects for the given HashMap hash (this is either flights or airports). Each parse type
    has different data to add to each object so it has been made abstract and is implemented by each of the child parse
    types. This function returns a new modified instance of the HashMap it was passed.
    */
    public abstract HashMap useCSVLine(String[] strLineArr, HashMap hash);


}
