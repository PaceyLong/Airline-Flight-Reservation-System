package Parser.parseTypes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


 /**
  *  The abstract "Strategy" class from the strategy pattern. Each type of parse inherits from this class by
  *  implementing useCSVLine.
  */
public abstract class CSVParse {

     /**
      * runs through the Parser lines and adds json data to objects within the flights or airports json array
      * @param path - path to get to the csv file stored locally
      */
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
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

     /**
      * creates, adds, and modifies objects for the given HashMap hash (this is either flights or airports or reservations).
      * Each parse type has different data to add to each object so it has been made abstract and is implemented by each of the child parse
      * types. This function returns a new modified instance of the HashMap it was passed.
      *
      * @param strLineArr - array of strings which correspond to specific fields
      */
    public abstract void useCSVLine(String[] strLineArr);


}
