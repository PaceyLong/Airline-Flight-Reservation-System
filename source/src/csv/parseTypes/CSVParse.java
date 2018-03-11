package csv.parseTypes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


/*
    The abstract "Strategy" class from the strategy pattern. Each type of parse inherits from this class by
    implementing useCSVLine.
 */
public abstract class CSVParse {

    //runs through the csv lines and adds json data to objects within the flights or airports json array
    public void parseCSV(String path){
        String line;

        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] strLineArr = line.split(",");

                //generate new fields or new objects to add to hashmaps
                useCSVLine(strLineArr);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
    creates, adds, and modifies objects for the given HashMap hash (this is either flights or airports). Each parse type
    has different data to add to each object so it has been made abstract and is implemented by each of the child parse
    types. This function returns a new modified instance of the HashMap it was passed.
    */
    public abstract void useCSVLine(String[] strLineArr);


}
