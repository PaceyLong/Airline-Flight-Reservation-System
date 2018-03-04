package src.Airports;

import java.util.HashMap;

/**
 * @author Joshua Ehling
 * Enforces Singleton Pattern
 *
 * Hashmap database to store Airport objects
 * Key: airport code --> Val: Airport object
 */
public class AirportsDB {
    /* attributes */
    private HashMap<String, Airport> airportsHashMap;

    /* Enforce Singleton Pattern */
    private static AirportsDB instance;

    /* Singleton Pattern accessor */
    public static AirportsDB getInstance() {
        if(instance == null) instance = new AirportsDB();
        return instance;
    }

    /**
     * Constructor
     */
    private AirportsDB(){
        airportsHashMap = new HashMap<>();
    }

    /**
     * Accessor for HashMap
     * @return HashMap
     */
    public HashMap<String, Airport> getAirportsDBHashMap() {
        return airportsHashMap;
    }

    /**
     * Add airport to Database
     * @param airportCode - unique airport identifier
     * @param airport - airport object
     */
    public void addAirport(String airportCode, Airport airport) {
        airportsHashMap.put(airportCode, airport);
    }

    /**
     * Remove airport from database with airportCode key
     * @param airportCode
     */
    public void removeAirport(String airportCode){
        airportsHashMap.remove(airportCode);
    }

    /**
     * Retrieve an airport from the database using it's airport code
     * @param airportCode
     * @return airport object
     */
    public Airport getAirport(String airportCode){
        return airportsHashMap.get(airportCode);
    }
}
