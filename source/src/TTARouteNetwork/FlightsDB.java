package TTARouteNetwork;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Joshua Ehling
 * Enforces Singleton Pattern
 * Database used to map Origin Airport --> ArrayList <Flights>
 */
public class FlightsDB {
    /* attributes */
    private HashMap<String, ArrayList<Flight>> flightsHashMap;

    /* Enforce Singleton Pattern */
    private static FlightsDB instance;

    /* Singleton Pattern Accessor */
    public static FlightsDB getInstance(){
        if(instance == null) instance = new FlightsDB();
        return instance;
    }

    /**
     * Constructor
     */
    private FlightsDB(){
        flightsHashMap = new HashMap<>();
    }
}
