package TTARouteNetwork;

import java.util.HashMap;

/**
 * @author Joshua Ehling
 * Enforces Singleton Pattern
 * Database used to map flight number --> ArrayList <Flights>
 */
public class FlightsDB {
    /* attributes */
    private HashMap<Integer, Flight> flightsHashMap;

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

    /**
     * Accessor for total set of Flight objects. Maps Flight # --> Flight Object
     * @param flightNumber - unique flight number identifier
     * @return ArrayList of Flight objects that share the origin airport
     */
    public Flight getFlight(int flightNumber){
        return flightsHashMap.get(flightNumber);
    }

    /**
     * Mutator method. Adds Flight objects to general HashMap based on Flight Number
     * @param flightNumber - unique flight ID
     * @param flight - Flight object
     */
    public void addFlight(int flightNumber, Flight flight){
        flightsHashMap.put(flightNumber, flight);
    }

    /**
     * Mutator method. Removes flight objects from general HashMap
     * @param flightNumber  unique flight ID
     */
    public void removeFlight(int flightNumber){
        flightsHashMap.remove(flightNumber);
    }
}
