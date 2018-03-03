package Airports;

import java.util.HashMap;

/**
 * @author Joshua Ehling
 * Hashmap database to store Airport objects
 * Key: airport code --> Val: Airport object
 */
public class AirportsDB {
    /* attributes */
    private HashMap<String, Airport> airportsDB;

    public AirportsDB(){
        airportsDB = new HashMap<>();
    }

    /**
     * Accessor for HashMap
     * @return HashMap
     */
    public HashMap<String, Airport> getAirportsDB() {
        return airportsDB;
    }

    /**
     * Add airport to Database
     * @param airportCode - unique airport identifier
     * @param airport - airport object
     */
    public void addAirport(String airportCode, Airport airport) {
        airportsDB.put(airportCode, airport);
    }

    /**
     * Remove airport from database with airportCode key
     * @param airportCode
     */
    public void removeAirport(String airportCode){
        airportsDB.remove(airportCode);
    }

    /**
     * Retrieve an airport from the database using it's airport code
     * @param airportCode
     * @return airport object
     */
    public Airport getAirport(String airportCode){
        return airportsDB.get(airportCode);
    }
}
