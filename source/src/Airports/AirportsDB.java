package Airports;

import Parser.parseTypes.AirportFAAParse;

import java.util.HashMap;

/**
 * @author Joshua Ehling
 * Enforces Singleton Pattern
 *
 * Hashmap database to store Airport objects
 * Key: airport code --> Val: Airport object
 */
public class AirportsDB {


    private static final String LOCAL = "local";
    private static final String FAA = "faa";

    /* attributes */
    private HashMap<String, Airport> airportsHashMap;
    private AirportFAAParse faaParse;
    private String airportService;

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
        faaParse = new AirportFAAParse();
        this.airportService = LOCAL;
    }

    /**
     * Setter for HashMap
     */
    public void setAirportsDBHashMap(HashMap<String, Airport> hash) {
        airportsHashMap = hash;
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
     * toggle airport service
     */
    public void switchAirportService(){
        this.airportService = this.airportService.equals(LOCAL)? FAA : LOCAL;
    }

    /**
     * return service (faa or local) being used
     * @return service
     */
    public String getAirportService(){
        return this.airportService;
    }

    /**
     * Retrieve an airport from the database using it's airport code
     * or from faa webservice
     * @param airportCode
     * @return airport object
     */
    public Airport getAirport(String airportCode){
        if(airportService.equals(LOCAL)){
            return airportsHashMap.get(airportCode);
        }

        //else if(airportService.equals(FAA)){
        return faaParse.getAirport(airportCode);

    }
}
